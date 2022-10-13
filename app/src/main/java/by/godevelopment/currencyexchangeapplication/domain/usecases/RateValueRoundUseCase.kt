package by.godevelopment.currencyexchangeapplication.domain.usecases

import android.util.Log
import by.godevelopment.currencyexchangeapplication.commons.ROUND_TO_PLACES
import by.godevelopment.currencyexchangeapplication.commons.TAG
import java.math.RoundingMode
import javax.inject.Inject

class RateValueRoundUseCase @Inject constructor() {

    operator fun invoke(input: Double): Double {
        val big = input.toBigDecimal()
        Log.i(TAG, "RateValueRoundUseCase: $input -> $big")
        return big.setScale(ROUND_TO_PLACES, RoundingMode.HALF_UP).toDouble()
    }
}
