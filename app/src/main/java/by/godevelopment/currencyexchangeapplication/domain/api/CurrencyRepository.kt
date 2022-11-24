package by.godevelopment.currencyexchangeapplication.domain.api

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun fetchLatestRates(): Flow<List<CurrencyModel>>
}