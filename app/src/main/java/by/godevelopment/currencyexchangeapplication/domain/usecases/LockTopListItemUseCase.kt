package by.godevelopment.currencyexchangeapplication.domain.usecases

import android.util.Log
import by.godevelopment.currencyexchangeapplication.commons.DOUBLE_ZERO_STUB
import by.godevelopment.currencyexchangeapplication.commons.TAG
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import javax.inject.Inject

class LockTopListItemUseCase  @Inject constructor() {

    operator fun invoke(list: List<CurrencyModel>): List<CurrencyModel> {
        return if (list.isNotEmpty()) {
            val first = list[0]
            val remainder = list.drop(1)
            val result = listOf(
                CurrencyModel(
                    id = first.id,
                    base = first.base,
                    // Lock DiffUtils in adapter by zero value
                    rate = DOUBLE_ZERO_STUB,
                    currencyDraw = first.currencyDraw,
                    currencyName = first.currencyName
                )
            ) + remainder
            Log.i(TAG, "lockTopListItemUseCase \n list = $list \n result = $result")
            result
        }
        else list
    }
}