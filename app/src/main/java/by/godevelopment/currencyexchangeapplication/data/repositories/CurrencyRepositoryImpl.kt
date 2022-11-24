package by.godevelopment.currencyexchangeapplication.data.repositories

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.commons.DOUBLE_ZERO_STUB
import by.godevelopment.currencyexchangeapplication.commons.STRING_ZERO_STUB
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
import by.godevelopment.currencyexchangeapplication.data.utils.toCurrencyModel
import by.godevelopment.currencyexchangeapplication.domain.api.CurrencyRepository
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyDataSource: CurrencyDataSource
) : CurrencyRepository {

    override fun fetchLatestRates(): Flow<List<CurrencyModel>> = currencyDataSource
        .fetchLatestRates()
        .map { list ->
            list
                .mapIndexed { index, currencyEntity ->
                    currencyEntity.toCurrencyModel(index)
                }
                .filter {
                    it.base != STRING_ZERO_STUB
                            && it.rate != DOUBLE_ZERO_STUB
                            && it.currencyName != R.string.message_error_data_load
                            && it.currencyDraw != R.drawable.ic_launcher_background
                }
        }
}
