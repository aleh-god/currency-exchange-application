package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

internal class MoveItemToTopListByBaseUseCaseTest {

    private val list = (0..3).map {
        CurrencyModel(
            id = it,
            rate = it * 1.1,
            base = "base$it",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        )
    }

    private val useCase = MoveItemToTopListByBaseUseCase()

    @Test
    fun `invoke move item to top list by base is correct`() {
        val newList = useCase(list, "base2")
        val topItem = newList.first()
        assertEquals(topItem.base, "base2")
        val remainder = newList.drop(1)
        remainder.forEach {
            assertNotEquals(it.base, "base2")
        }
    }
}
