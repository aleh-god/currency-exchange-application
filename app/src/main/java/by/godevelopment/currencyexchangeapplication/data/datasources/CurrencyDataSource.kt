package by.godevelopment.currencyexchangeapplication.data.datasources

import by.godevelopment.currencyexchangeapplication.commons.TASK_INTERVAL
import by.godevelopment.currencyexchangeapplication.data.remoteapi.CurrencyApi
import by.godevelopment.currencyexchangeapplication.data.entities.CurrencyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyDataSource @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val ioDispatcher: CoroutineDispatcher,
    private val refreshIntervalMs: Long = TASK_INTERVAL
) {
    val latestRates: Flow<List<CurrencyEntity>> = flow {
        while(true) {
            val latestRates = currencyApi.fetchLatestRates()
            emit(latestRates) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }.flowOn(ioDispatcher)
}
