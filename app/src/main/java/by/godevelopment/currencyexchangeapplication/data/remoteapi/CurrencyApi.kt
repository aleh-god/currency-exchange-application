package by.godevelopment.currencyexchangeapplication.data.remoteapi

import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import retrofit2.http.GET

interface CurrencyApi {

    @GET("currency")
    suspend fun fetchRates(): List<CurrencyEntity>
}
