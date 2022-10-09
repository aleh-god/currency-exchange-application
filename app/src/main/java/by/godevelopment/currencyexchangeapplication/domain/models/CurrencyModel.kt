package by.godevelopment.currencyexchangeapplication.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CurrencyModel(
    var rate : Double,
    var base : String,
    @StringRes
    val currencyName: Int,
    @DrawableRes
    val currencyDraw: Int,
    val isTop: Boolean = false
)
