package by.godevelopment.currencyexchangeapplication.data.utils

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test

internal class DataUtilsKtTest {

    private val entity: CurrencyEntity = CurrencyEntity(
        date = 1659706268,
        rate = 0.032063693827542705,
        base = "USD"
    )

    @Test
    fun `toCurrencyModel after conversion returns correct data`() {
        val model: CurrencyModel = entity.toCurrencyModel(0)

        assertEquals(entity.rate!!, model.rate,  0.0)
        assertEquals(entity.base!!, model.base)
        assertEquals(R.string.currency_usd_name, model.currencyName)
        assertEquals(R.drawable.ic_usd_flag, model.currencyDraw)
    }
}
