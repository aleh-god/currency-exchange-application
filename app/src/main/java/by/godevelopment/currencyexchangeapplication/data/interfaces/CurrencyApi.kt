package by.godevelopment.currencyexchangeapplication.data.interfaces

import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import retrofit2.http.GET

interface CurrencyApi {

    @GET("currency")
    suspend fun fetchRates(): List<CurrencyApiModel>
}
