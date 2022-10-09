package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.data.repositories.CurrencyRepositoryImpl
import by.godevelopment.currencyexchangeapplication.domain.api.CurrencyRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindCurrencyRepositoryImpl_to_CurrencyRepository(
        currencyRepositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
}
