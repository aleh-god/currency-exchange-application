package by.godevelopment.currencyexchangeapplication.data.interfaces

import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDataSource {

    fun fetchLatestRates(): Flow<List<CurrencyEntity>>
}
