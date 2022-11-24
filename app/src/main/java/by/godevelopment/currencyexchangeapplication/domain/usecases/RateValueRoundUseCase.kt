package by.godevelopment.currencyexchangeapplication.domain.usecases

import java.math.RoundingMode
import javax.inject.Inject

class RateValueRoundUseCase @Inject constructor(
    private val roundToPlace: Int
) {

    operator fun invoke(input: Double): Double {
        val big = input.toBigDecimal()
        return big.setScale(roundToPlace, RoundingMode.HALF_UP).toDouble()
    }
}
