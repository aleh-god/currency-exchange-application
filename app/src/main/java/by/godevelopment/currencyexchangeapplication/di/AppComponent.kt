package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: MainFragment)
}
