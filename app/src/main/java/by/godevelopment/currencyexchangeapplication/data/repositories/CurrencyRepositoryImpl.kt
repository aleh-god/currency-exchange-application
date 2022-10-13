package by.godevelopment.currencyexchangeapplication.data.repositories

import by.godevelopment.currencyexchangeapplication.data.datasources.CurrencyDataSource
import by.godevelopment.currencyexchangeapplication.data.utils.toCurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.api.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource
) : CurrencyRepository {

    override suspend fun fetchLatestRates(): Flow<List<CurrencyModel>> = currencyDataSource
        .fetchLatestRates()
        .map { list ->
            list
                .filter { it.base != null || it.rate != null }
                .mapIndexed { index, currencyEntity ->
                    currencyEntity.toCurrencyModel(index)
                }
        }
}
