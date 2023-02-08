package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import java.math.BigDecimal
import javax.inject.Inject

/**
 Use this code block in viewModel
                .combine(topPositionCurrencyBaseState) { list, topPositionCurrencyBase ->
                    topPositionCurrencyBase?.let { lockTopListItemUseCase(list) } ?: list
                }
 **/

@Deprecated("The solution has been used before payloads")
class LockTopListItemUseCase  @Inject constructor() {

    operator fun invoke(list: List<CurrencyModel>): List<CurrencyModel> {
        return if (list.isNotEmpty()) {
            val first = list[0]
            val remainder = list.drop(1)
            listOf(
                CurrencyModel(
                    id = first.id,
                    base = first.base,
                    // Lock DiffUtils in adapter by zero value
                    rate = BigDecimal.ZERO,
                    currencyDraw = first.currencyDraw,
                    currencyName = first.currencyName
                )
            ) + remainder
        }
        else list
    }
}
