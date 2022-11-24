package by.godevelopment.currencyexchangeapplication.data.utils

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.commons.DOUBLE_ZERO_STUB
import by.godevelopment.currencyexchangeapplication.commons.STRING_ZERO_STUB
import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Test

internal class DataUtilsKtNullTest {

    private val entity: CurrencyEntity = CurrencyEntity(
        date = null,
        rate = null,
        base = null
    )

    @Test
    fun `toCurrencyModel after nullable data conversion returns correct stub`() {
        val model: CurrencyModel = entity.toCurrencyModel(0)

        assertEquals(DOUBLE_ZERO_STUB, model.rate,  0.0)
        assertEquals(STRING_ZERO_STUB, model.base)
        assertEquals(R.string.message_error_data_load, model.currencyName)
        assertEquals(R.drawable.ic_launcher_background, model.currencyDraw)
    }
}
