package by.godevelopment.currencyexchangeapplication.data.interfaces

import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import kotlinx.coroutines.flow.Flow

interface CurrencyDataSource {

    fun fetchLatestRates(): Flow<List<CurrencyApiModel>>
}
