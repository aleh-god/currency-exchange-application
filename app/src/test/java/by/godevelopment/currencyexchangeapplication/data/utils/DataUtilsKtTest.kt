package by.godevelopment.currencyexchangeapplication.data.utils

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class DataUtilsKtTest {

    @Test
    fun `toCurrencyModel after conversion returns correct data`() {

        val entity = CurrencyApiModel(
            date = 1659706268,
            rate = 0.032063693827542705,
            base = "USD"
        )

        val model: CurrencyModel = entity.toCurrencyModel(0)

        assertEquals(entity.rate!!, model.rate.toDouble(),  0.0)
        assertEquals(entity.base!!, model.base)
        assertEquals(R.string.currency_usd_name, model.currencyName)
        assertEquals(R.drawable.ic_usd_flag, model.currencyDraw)
    }

    @Test
    fun `toCurrencyModel after nullable data conversion returns correct stub`() {

        val entity = CurrencyApiModel(
            date = null,
            rate = null,
            base = null
        )

        val model: CurrencyModel = entity.toCurrencyModel(0)

        assertEquals(BigDecimal.ZERO, model.rate)
        assertEquals(String(), model.base)
        assertEquals(R.string.message_error_data_load, model.currencyName)
        assertEquals(R.drawable.ic_launcher_background, model.currencyDraw)
    }
}
