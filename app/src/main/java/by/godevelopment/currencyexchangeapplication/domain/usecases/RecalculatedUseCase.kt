package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class RecalculatedUseCase @Inject constructor(

) {
    suspend operator fun invoke(input: List<CurrencyModel>): List<CurrencyModel> {
        return TODO("Impl RecalculatedUseCase")
    }
}
