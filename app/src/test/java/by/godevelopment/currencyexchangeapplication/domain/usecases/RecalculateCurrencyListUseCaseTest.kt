package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class RecalculateCurrencyListUseCaseTest {

    private val ROUND_TO_PLACES = 5
    private val value = "5"
    private val input: List<CurrencyModel> = listOf(
        CurrencyModel(
            id = 0,
            rate = BigDecimal.valueOf(2.0),
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        ),
        CurrencyModel(
            id = 1,
            rate = BigDecimal.valueOf(3.0),
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        ),
    )
    private val useCase = RecalculateCurrencyListUseCase(ROUND_TO_PLACES)

    @Test
    fun `invoke if value not double return same list`() {
        val actual = useCase.invoke(input, "is string")
        assertEquals(input, actual)
    }

    @Test
    fun `invoke return calc rate value is correct`() {
        val actual = useCase.invoke(input, value).drop(1).first().rate
        assertEquals(3.33333, actual.toDouble(), 0.001)
    }
}
