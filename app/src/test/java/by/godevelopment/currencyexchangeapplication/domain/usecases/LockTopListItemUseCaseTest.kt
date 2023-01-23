package by.godevelopment.currencyexchangeapplication.domain.usecases

import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.domain.models.CurrencyModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.math.BigDecimal

internal class LockTopListItemUseCaseTest {

    private val list = (0..3).map {
        CurrencyModel(
            id = it,
            rate = BigDecimal.valueOf(0.032063693827542705),
            base = "USD",
            currencyName = R.string.currency_usd_name,
            currencyDraw = R.drawable.ic_usd_flag
        )
    }

    private val useCase = LockTopListItemUseCase()

    @Test
    fun `invoke set base zero value in fist item of list is correct`() {
        val lockList = useCase(list)
        val (firstItemList, remainder) = lockList.partition { it.id == 0 }
        assertEquals(BigDecimal.ZERO.toDouble(), firstItemList.first().rate.toDouble(), 0.0)
        remainder.forEach {
            assertNotEquals(BigDecimal.ZERO.toDouble(), it.rate.toDouble(), 0.0)
        }
    }
}
