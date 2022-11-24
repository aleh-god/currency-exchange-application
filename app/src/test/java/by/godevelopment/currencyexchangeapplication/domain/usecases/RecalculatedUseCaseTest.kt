package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RecalculatedUseCaseTest {

    private val value = "10.0"
    private val input: List<CurrencyModel> = listOf(
        CurrencyModel(
            id = 0,
            rate = 0.1,
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        ),
        CurrencyModel(
            id = 0,
            rate = 0.3,
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        ),
    )
    private val useCase = RecalculatedUseCase()

    @Test
    fun `invoke if value not double return same list`() {
        val actual = useCase.invoke(input, "is string")
        assertEquals(input, actual)
    }

    @Test
    fun `invoke return calc rate value is correct`() {
        val actual = useCase.invoke(input, value).drop(1).first().rate
        assertEquals(3.333, actual, 0.001)
    }
}
