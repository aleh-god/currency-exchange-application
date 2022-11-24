package by.godevelopment.currencyexchangeapplication.di

import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    private val ROUND_TO_PLACES = 5

    @Provides
    fun provideRoundToPlace(): Int = ROUND_TO_PLACES
}
