package by.godevelopment.currencyexchangeapplication

import android.app.Application
import android.content.Context
import by.godevelopment.currencyexchangeapplication.di.AppComponent
import by.godevelopment.currencyexchangeapplication.di.DaggerAppComponent

class CurrencyExchangeApp: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is CurrencyExchangeApp -> appComponent
        else -> this.applicationContext.appComponent
    }
