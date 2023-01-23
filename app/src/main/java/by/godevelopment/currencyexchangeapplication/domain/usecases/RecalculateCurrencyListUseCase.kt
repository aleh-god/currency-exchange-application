package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class RecalculateCurrencyListUseCase @Inject constructor(
    private val roundToPlace: Int
) {

    operator fun invoke(models: List<CurrencyModel>, value: String): List<CurrencyModel> {

        value.toDoubleOrNull()?.let {
            val baseCost = models
                .first()
                .rate
                .multiply(
                    BigDecimal.valueOf(it)
                )
            return models.map { model ->
                try {
                    val newRate = baseCost.divide(model.rate, roundToPlace, RoundingMode.HALF_UP)
                    model.copy(rate = newRate)
                } catch (e: Exception) {
                    model
                }
            }
        }
        return models
    }
}
