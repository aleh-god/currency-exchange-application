package by.godevelopment.currencyexchangeapplication.data.remoteapi

import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity

interface CurrencyApi {
    fun fetchLatestRates(): List<CurrencyEntity> {
        // https://us-central1-epam-laba-13-1527598553135.cloudfunctions.net/myWebsiteBackend/api/currency/
        TODO("Impl CurrencyApi")
    }
}
