package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.data.datasources.CurrencyDataSourceImpl
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
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

    @Suppress("FunctionName")
    @Binds
    fun bindCurrencyDataSourceImpl_to_CurrencyDataSource(
        currencyDataSourceImpl: CurrencyDataSourceImpl
    ): CurrencyDataSource
}
