package by.godevelopment.currencyexchangeapplication.data.entities

import com.google.gson.annotations.SerializedName

data class CurrencyEntity (
    @SerializedName("date" ) var date : Long? = null,
    @SerializedName("rate" ) var rate : Double? = null,
    @SerializedName("base" ) var base : String? = null
)
