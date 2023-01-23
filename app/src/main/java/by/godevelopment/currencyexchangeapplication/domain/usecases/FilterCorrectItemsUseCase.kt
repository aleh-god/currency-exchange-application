package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import java.math.BigDecimal
import javax.inject.Inject

class FilterCorrectItemsUseCase @Inject constructor() {

    operator fun invoke(list: List<CurrencyModel>): List<CurrencyModel> {

        return list.filter {
            it.base.isNotEmpty()
                    && !BigDecimal.ZERO.equals(it.rate)
                    && it.currencyName != R.string.message_error_data_load
                    && it.currencyDraw != R.drawable.ic_launcher_background
        }
    }
}
