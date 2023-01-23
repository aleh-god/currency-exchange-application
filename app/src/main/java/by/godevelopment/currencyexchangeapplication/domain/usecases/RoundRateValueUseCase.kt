package by.godevelopment.currencyexchangeapplication.domain.usecases

import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class RoundRateValueUseCase @Inject constructor(
    private val roundToPlace: Int
) {

    operator fun invoke(input: BigDecimal): BigDecimal {
        val roundingRange = if (input.scale() > roundToPlace) roundToPlace else input.scale()
        return input.setScale(roundingRange, RoundingMode.HALF_UP)
    }
}
