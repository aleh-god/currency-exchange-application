package by.godevelopment.currencyexchangeapplication.data.repositories

import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
import by.godevelopment.currencyexchangeapplication.data.utils.toCurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.interfaces.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource
) : CurrencyRepository {

    override fun fetchLatestRates(): Flow<List<CurrencyModel>> = currencyDataSource
        .fetchLatestRates()
        .map { list ->
            list.mapIndexed { index, currencyApiModel ->
                currencyApiModel.toCurrencyModel(index)
            }
        }
}
