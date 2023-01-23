package by.godevelopment.currencyexchangeapplication.data.datasources

import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

internal class CurrencyRemoteDataSourceImplTest {

    class MyFakeCurrencyApi : CurrencyApi {

        private var invokeCount: Int = 0

        private val entity = CurrencyApiModel(
            date = 1659706268,
            rate = 0.032063693827542705,
            base = "USD"
        )

        override suspend fun fetchRates(): List<CurrencyApiModel> {
            invokeCount++
            return listOf(entity)
        }
    }

    private val currencyRemoteDataSourceImpl: CurrencyRemoteDataSourceImpl = CurrencyRemoteDataSourceImpl(
        currencyApi = MyFakeCurrencyApi(),
        refreshIntervalMs = 5000L,
        ioDispatcher = Dispatchers.IO //StandardTestDispatcher() // UnconfinedTestDispatcher() //
    )

    @Test
    fun `fetchLatestRates after invoke delay is into near 5000 millis`() = runBlocking {
        val totalExecutionTime = measureTimeMillis {
            currencyRemoteDataSourceImpl.fetchLatestRates().take(2).collect()
        }
        println("totalExecutionTime = $totalExecutionTime")
        val expected = totalExecutionTime in 5000L..5500L
        assert(expected)
    }
}
