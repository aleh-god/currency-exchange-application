package by.godevelopment.currencyexchangeapplication.domain.usecases

import android.util.Log
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class RecalculatedUseCase @Inject constructor(
) {
    operator fun invoke(input: List<CurrencyModel>, inputValue: String): List<CurrencyModel> {
        inputValue.toDoubleOrNull()?.let {
            val baseCost = input.first().rate * it
            Log.i(TAG, "RecalculatedUseCase: $input = $baseCost")
            return input.map { model ->
                try {
                    model.copy(rate = baseCost / model.rate)
                } catch (e: Exception) {
                    Log.i(TAG, "RecalculatedUseCase catch: ${e.message}")
                    model
                }
            }
        }
        return input
    }
}
