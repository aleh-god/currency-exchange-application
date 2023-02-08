package by.godevelopment.currencyexchangeapplication.data.repositories

import by.godevelopment.currencyexchangeapplication.data.models.CurrencyApiModel
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class CurrencyRepositoryImplTest {

    private val entity = CurrencyApiModel(
        date = 1659706268,
        rate = 0.032063693827542705,
        base = "USD"
    )

    class MyFakeCurrencyDataSource(
        private val nestedEntity: CurrencyApiModel
    ) : CurrencyDataSource {

        var sourceWasInvoked: Boolean = false
            private set

        override fun fetchLatestRates(): Flow<List<CurrencyApiModel>> = flow {
            sourceWasInvoked = true
            emit(listOf(nestedEntity))
        }
    }

    private val dataSource: MyFakeCurrencyDataSource by lazy { MyFakeCurrencyDataSource(entity) }

    private val repositoryImpl: CurrencyRepositoryImpl by lazy { CurrencyRepositoryImpl(dataSource) }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchLatestRates when calling it works correctly`() = runTest {
        val actual = repositoryImpl.fetchLatestRates().first().size
        assertEquals(1, actual)
        assert(dataSource.sourceWasInvoked)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchLatestRates when calling it passes the correct data`() = runTest {
        val list = repositoryImpl.fetchLatestRates().first()
        val model = list.first()
        assertEquals(BigDecimal.valueOf(entity.rate!!), model.rate)
    }
}
