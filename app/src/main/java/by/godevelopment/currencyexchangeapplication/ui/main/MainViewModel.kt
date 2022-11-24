package by.godevelopment.currencyexchangeapplication.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.domain.api.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.usecases.LockTopListItemUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.MoveItemToTopListByBaseUseCase
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
    private val moveItemToTopListByBaseUseCase: MoveItemToTopListByBaseUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<Int>()
    val uiEvent: Flow<Int> = _uiEvent.receiveAsFlow()

    private val topPositionCurrencyRateState: MutableStateFlow<String?> = MutableStateFlow(null)
    private val topPositionCurrencyBaseState: MutableStateFlow<String?> = MutableStateFlow(null)

    private var fetchJob: Job? = null

    init {
        Log.i(TAG, "MainViewModel: init")
        fetchDataList()
    }

    private fun fetchDataList() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            currencyRepository.fetchLatestRates()
                .onStart { _uiState.update { it.copy(isFetchingData = true) } }
                .catch { exception ->
                    Log.i(TAG, "viewModelScope.catch ${exception.message}")
                    _uiState.update { it.copy(isFetchingData = false) }
                    _uiEvent.send(R.string.message_error_data_load)
                }
                .combine(topPositionCurrencyBaseState) { list, topPositionCurrencyBase ->
                    _uiState.update { it.copy(topHasFocus = (topPositionCurrencyBase != null)) }
                    moveItemToTopListByBaseUseCase(list, topPositionCurrencyBase)
                }
                .combine(topPositionCurrencyRateState) { list, topPositionCurrencyValue ->
                    val result = if(topPositionCurrencyValue != null) recalculatedUseCase(list, topPositionCurrencyValue)
                    else list
                    Log.i(TAG, "fetchDataList: onTextChange rate = $topPositionCurrencyValue \n list = $list \n result = $result")
                    result
                }
                .combine(topPositionCurrencyBaseState) { list, topPositionCurrencyBase ->
                    if (topPositionCurrencyBase != null) lockTopListItemUseCase(list)
                    else list
                }
                .catch { exception ->
                    Log.i(TAG, "viewModelScope.catch ${exception.message}")
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
        private val moveItemToTopListByBaseUseCase: MoveItemToTopListByBaseUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                currencyRepository,
                recalculatedUseCase,
                lockTopListItemUseCase,
                moveItemToTopListByBaseUseCase
            ) as T
        }
    }
}
