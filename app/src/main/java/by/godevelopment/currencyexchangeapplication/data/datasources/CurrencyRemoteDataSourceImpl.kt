package by.godevelopment.currencyexchangeapplication.data.datasources

import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyApi
import by.godevelopment.currencyexchangeapplication.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val refreshIntervalMs: Long
) : CurrencyDataSource {

    override fun fetchLatestRates(): Flow<List<CurrencyApiModel>> = flow {
        while(true) {
            val latestRates = currencyApi.fetchRates()
            emit(latestRates)
            delay(refreshIntervalMs)
        }
    }.flowOn(ioDispatcher)
}
