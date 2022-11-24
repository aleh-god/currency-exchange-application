package by.godevelopment.currencyexchangeapplication.data.utils

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.commons.CurrencyBases
import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel

fun CurrencyEntity.toCurrencyModel(index: Int): CurrencyModel {
    return CurrencyModel(
        id = index,
        rate = rate ?: 0.0,
        base = base ?: "",
        currencyName = CurrencyBases.map[base]?.currencyName ?: R.string.message_error_data_load,
        currencyDraw = CurrencyBases.map[base]?.currencyDraw ?: R.drawable.ic_launcher_background
    )
}
