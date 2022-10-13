package by.godevelopment.currencyexchangeapplication.domain.usecases

import android.util.Log
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class MoveItemToTopListByBaseUseCase @Inject constructor() {

    operator fun invoke(
        list: List<CurrencyModel>,
        topPositionCurrencyBase: String?
    ): List<CurrencyModel> {
        val result = list
            .partition { it.base == topPositionCurrencyBase }
            .let { pair -> pair.first + pair.second }
        Log.i(TAG, "moveItemToTopListByBaseUseCase = $topPositionCurrencyBase \n list = $list \n result = $result")
        return result
    }
}