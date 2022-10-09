package by.godevelopment.currencyexchangeapplication.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class, DispatcherModule::class])
class AppModule