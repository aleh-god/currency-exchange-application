package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class MoveItemToTopListByBaseUseCase @Inject constructor() {

    operator fun invoke(
        list: List<CurrencyModel>,
        topPositionCurrencyBase: String?
    ): List<CurrencyModel> {
        return list
            .partition { it.base == topPositionCurrencyBase }
            .let { pair -> pair.first + pair.second }
    }
}
