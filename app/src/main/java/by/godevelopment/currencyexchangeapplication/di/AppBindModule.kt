package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.data.datasources.CurrencyRemoteDataSourceImpl
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyDataSource
import by.godevelopment.currencyexchangeapplication.data.repositories.CurrencyRepositoryImpl
import by.godevelopment.currencyexchangeapplication.domain.interfaces.CurrencyRepository
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
        currencyRemoteDataSourceImpl: CurrencyRemoteDataSourceImpl
    ): CurrencyDataSource
}
