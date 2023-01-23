package by.godevelopment.currencyexchangeapplication.domain.interfaces

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun fetchLatestRates(): Flow<List<CurrencyModel>>
}
