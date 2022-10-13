package by.godevelopment.currencyexchangeapplication.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CurrencyModel(
    val id: Int,
    val rate : Double,
    val base : String,
    @StringRes
    val currencyName: Int,
    @DrawableRes
    val currencyDraw: Int
)
