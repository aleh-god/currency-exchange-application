package by.godevelopment.currencyexchangeapplication.data.datasources

import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import by.godevelopment.currencyexchangeapplication.data.remoteapi.CurrencyApi
import by.godevelopment.currencyexchangeapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyDataSource @Inject constructor(
    private val currencyApi: CurrencyApi,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val refreshIntervalMs: Long
) {
    suspend fun fetchLatestRates(): Flow<List<CurrencyEntity>> = flow {
        while(true) {
            val latestRates = currencyApi.fetchRates()
            emit(latestRates)
            delay(refreshIntervalMs)
        }
    }.flowOn(ioDispatcher)
}
