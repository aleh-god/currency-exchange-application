package by.godevelopment.currencyexchangeapplication.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

internal class RoundRateValueUseCaseTest {

    private val ROUND_TO_PLACES = 5
    private val useCase = RoundRateValueUseCase(ROUND_TO_PLACES)

    @Test
    fun `invoke rounding to five digits after zero is correct`() {
        val rate = BigDecimal.valueOf(0.032063693827542705)
        val roundDigit = useCase(rate)
        val (a, b) = roundDigit.toString().split(".")
        val countDigits = b.length
        assertEquals(countDigits, 5)
    }

    @Test
    fun `invoke rounding to three digits after zero is correct`() {
        val rate = BigDecimal.valueOf(0.032)
        val roundDigit = useCase(rate)
        val (a, b) = roundDigit.toString().split(".")
        val countDigits = b.length
        assertEquals(countDigits, 3)
    }

    @Test
    fun `invoke math low rounding digits after zero is correct`() {
        val rate = BigDecimal.valueOf(0.032063)
        val roundDigit = useCase(rate)
        val lastDigit = roundDigit.toString().takeLast(1)
        assertEquals(lastDigit, "6")
    }

    @Test
    fun `invoke math high rounding digits after zero is correct`() {
        val rate = BigDecimal.valueOf(0.032066)
        val roundDigit = useCase(rate)
        val lastDigit = roundDigit.toString().takeLast(1)
        assertEquals(lastDigit, "7")
    }

    @Test
    fun `invoke math middle rounding digits after zero is correct`() {
        val rate = BigDecimal.valueOf(0.032065)
        val roundDigit = useCase(rate)
        val lastDigit = roundDigit.toString().takeLast(1)
        assertEquals(lastDigit, "7")
    }
}
