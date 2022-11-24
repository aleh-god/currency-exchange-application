package by.godevelopment.currencyexchangeapplication.di

import dagger.Module

@Module(includes = [NetworkModule::class, DomainModule::class, AppBindModule::class, DispatcherModule::class])
class AppModule