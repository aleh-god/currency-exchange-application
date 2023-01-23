package by.godevelopment.currencyexchangeapplication.data.utils

import by.godevelopment.currencyexchangeapplication.data.datasources.CurrencyResDataSource
import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import java.math.BigDecimal

fun CurrencyApiModel.toCurrencyModel(index: Int): CurrencyModel = CurrencyModel(
    id = index,
    rate = rate?.let { BigDecimal.valueOf(it) } ?: BigDecimal.ZERO,
    base = base ?: String(),
    currencyName = CurrencyResDataSource.getCurrencyNameOrDefaultBy(base),
    currencyDraw = CurrencyResDataSource.getCurrencyDrawOrDefaultBy(base)
)
