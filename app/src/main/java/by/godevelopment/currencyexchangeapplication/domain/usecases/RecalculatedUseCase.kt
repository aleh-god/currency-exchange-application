package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class RecalculatedUseCase @Inject constructor(
) {
    operator fun invoke(input: List<CurrencyModel>, inputValue: String): List<CurrencyModel> {
        inputValue.toDoubleOrNull()?.let {
            val baseCost = input.first().rate * it
            return input.map { model ->
                try {
                    model.copy(rate = baseCost / model.rate)
                } catch (e: Exception) {
                    model
                }
            }
        }
        return input
    }
}
