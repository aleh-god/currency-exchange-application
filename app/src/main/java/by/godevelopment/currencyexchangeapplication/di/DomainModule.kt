package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.domain.usecases.RecalculateCurrencyListUseCase
import by.godevelopment.currencyexchangeapplication.domain.usecases.RoundRateValueUseCase
import dagger.Module
import dagger.Provides

private const val ROUND_TO_PLACES = 5
@Module
class DomainModule {

    @Provides
    fun provideRoundRateValueUseCase(): RoundRateValueUseCase =
        RoundRateValueUseCase(ROUND_TO_PLACES)

    @Provides
    fun provideRecalculateCurrencyListUseCase(): RecalculateCurrencyListUseCase =
        RecalculateCurrencyListUseCase(ROUND_TO_PLACES)
}
