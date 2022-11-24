package by.godevelopment.currencyexchangeapplication.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.api.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.usecases.LockTopListItemUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.MoveItemToTopListByBaseUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.RateValueRoundUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.RecalculatedUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel (
    private val currencyRepository: CurrencyRepository,
    private val recalculatedUseCase: RecalculatedUseCase,
    private val lockTopListItemUseCase: LockTopListItemUseCase,
    private val moveItemToTopListByBaseUseCase: MoveItemToTopListByBaseUseCase,
    private val rateValueRoundUseCase: RateValueRoundUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<Int>()
    val uiEvent: Flow<Int> = _uiEvent.receiveAsFlow()

    private val topPositionCurrencyRateState: MutableStateFlow<String?> = MutableStateFlow(null)
    private val topPositionCurrencyBaseState: MutableStateFlow<String?> = MutableStateFlow(null)

    private var fetchJob: Job? = null

    init {
        fetchDataList()
    }

    private fun fetchDataList() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            currencyRepository.fetchLatestRates()
                .onStart { _uiState.update { it.copy(isFetchingData = true) } }
                .catch { _ ->
                    _uiState.update { it.copy(isFetchingData = false) }
                    _uiEvent.send(R.string.message_error_data_load)
                }
                .combine(topPositionCurrencyBaseState) { list, topPositionCurrencyBase ->
                    topPositionCurrencyBase?.let {
                        _uiState.update { it.copy(topHasFocus = true) }
                        moveItemToTopListByBaseUseCase(list, topPositionCurrencyBase)
                    } ?: list
                }
                .combine(topPositionCurrencyRateState) { list, topPositionCurrencyValue ->
                    topPositionCurrencyValue?.let {
                        recalculatedUseCase(list, topPositionCurrencyValue)
                    } ?: list
                }
                .combine(topPositionCurrencyBaseState) { list, topPositionCurrencyBase ->
                    topPositionCurrencyBase?.let { lockTopListItemUseCase(list) } ?: list
                }
                .map { list ->
                    list.map { it.copy(rate = rateValueRoundUseCase(it.rate)) }
                }
                .catch { _ ->
                    _uiState.update { it.copy(isFetchingData = false) }
                    _uiEvent.send(R.string.message_error_data_calculated)
                }
                .collect { list ->
                    _uiState.update { it.copy(isFetchingData = false, dataList = list) }
                }
        }
    }

    fun onClickItem(value: String) {
        topPositionCurrencyRateState.value = null
        topPositionCurrencyBaseState.value = value
    }
    fun onTextChange(value: String) {
        topPositionCurrencyRateState.value = value
    }

    fun reloadDataList() {
        fetchDataList()
    }

    data class UiState(
        val isFetchingData: Boolean = false,
        val dataList: List<CurrencyModel> = listOf(),
        val topHasFocus: Boolean = false
    )

    class Factory @Inject constructor(
        private val currencyRepository: CurrencyRepository,
        private val recalculatedUseCase: RecalculatedUseCase,
        private val lockTopListItemUseCase: LockTopListItemUseCase,
        private val moveItemToTopListByBaseUseCase: MoveItemToTopListByBaseUseCase,
        private val rateValueRoundUseCase: RateValueRoundUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                currencyRepository,
                recalculatedUseCase,
                lockTopListItemUseCase,
                moveItemToTopListByBaseUseCase,
                rateValueRoundUseCase
            ) as T
        }
    }
}
