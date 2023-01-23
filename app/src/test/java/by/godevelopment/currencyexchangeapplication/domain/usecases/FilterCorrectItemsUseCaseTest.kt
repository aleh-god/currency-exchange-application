package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class FilterCorrectItemsUseCaseTest {

    private val usecase = FilterCorrectItemsUseCase()

    private val list = listOf<CurrencyModel>(
        CurrencyModel(
            id = 0,
            rate = BigDecimal.valueOf(0.0),
            base = "",
            currencyName = R.string.message_error_data_load,
            currencyDraw = R.drawable.ic_launcher_background
        ),
        CurrencyModel(
            id = 1,
            rate = BigDecimal.valueOf(0.032063693827542705),
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        )
    )

    @Test
    operator fun invoke() {
        val model = usecase(list).first()

        assertEquals(0.032063693827542705, model.rate.toDouble(),  0.0)
        assertEquals("USD", model.base)
        assertEquals(R.string.currency_usd_name, model.currencyName)
        assertEquals(R.drawable.ic_usd_flag, model.currencyDraw)
    }
}