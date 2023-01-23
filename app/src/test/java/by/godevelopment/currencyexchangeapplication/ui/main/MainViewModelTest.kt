package by.godevelopment.currencyexchangeapplication.ui.main

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.interfaces.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.usecases.FilterCorrectItemsUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.RecalculateCurrencyListUseCase
import by.godevelopment.currencyexchangeapplication.testutils.ViewModelTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class MainViewModelTest : ViewModelTest() {

    @MockK
    lateinit var currencyRepository: CurrencyRepository

    private lateinit var mockFlow: Flow<List<CurrencyModel>>
    private lateinit var viewModel: MainViewModel

    private val inputList = listOf(
        CurrencyModel(
            id = 0,
            rate = BigDecimal.valueOf(100.0),
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        ),
        CurrencyModel(
            id = 1,
            rate = BigDecimal.valueOf(120.0),
            base = "EUR",
            currencyName = R.string.currency_eur_name,
            currencyDraw = R.drawable.ic_eur_flag
        )
    )

    private val filterCorrectItemsUseCase = FilterCorrectItemsUseCase()
    private val recalculateCurrencyListUseCase = RecalculateCurrencyListUseCase(5)

    private fun setUp() {
        every { currencyRepository.fetchLatestRates() } returns mockFlow


        viewModel = MainViewModel(
            currencyRepository = currencyRepository,
            recalculateCurrencyListUseCase = recalculateCurrencyListUseCase,
            moveItemToTopListByBaseUseCase = moveItemToTopListByBaseUseCase,
            roundRateValueUseCase = roundRateValueUseCase,
            filterCorrectItemsUseCase = filterCorrectItemsUseCase
        )
    }

    @Test
    fun `init viewModel fetch data from data layer`() {

        val expectedList = listOf(
            CurrencyModel(
                id = 0,
                rate = BigDecimal.ZERO, // recalculatedUseCase is mocked. He does not work.
                base = "USD",
                currencyName = R.string.currency_usd_name,
                currencyDraw = R.drawable.ic_usd_flag
            ),
            CurrencyModel(
                id = 1,
                rate = BigDecimal.ZERO, // recalculatedUseCase is mocked. He does not work.
                base = "EUR",
                currencyName = R.string.currency_eur_name,
                currencyDraw = R.drawable.ic_eur_flag
            )
        )

        mockFlow = MutableStateFlow(inputList)
        setUp()

        val result = viewModel.uiState.value.dataList

        assertEquals(expectedList, result)
        verify(exactly = 1) { currencyRepository.fetchLatestRates() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `throw Exception in data flow and test uiEvent channel`() {

        mockFlow = flow { throw IllegalStateException() }
        setUp()

        runTest {
            val result = viewModel.uiEvent.first()

            assertEquals(R.string.message_error_data_load, result) // 2131689586
            verify(exactly = 1) { currencyRepository.fetchLatestRates() }
        }
    }

    @Test
    fun `reloadDataList is correct`() {

        mockFlow = MutableStateFlow(emptyList())
        setUp()

        viewModel.reloadDataList()

        verify(exactly = 2) {
            currencyRepository.fetchLatestRates()
        }
    }
}