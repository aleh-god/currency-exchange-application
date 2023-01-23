package by.godevelopment.currencyexchangeapplication.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.math.BigDecimal

data class CurrencyModel(
    val id: Int,
    val rate : BigDecimal,
    val base : String,
    @StringRes
    val currencyName: Int,
    @DrawableRes
    val currencyDraw: Int
)
