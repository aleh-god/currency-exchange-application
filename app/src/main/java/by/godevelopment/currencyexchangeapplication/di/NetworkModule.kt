package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.commons.BASE_URL
import by.godevelopment.currencyexchangeapplication.commons.TASK_INTERVAL
import by.godevelopment.currencyexchangeapplication.data.remoteapi.CurrencyApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideRefreshInterval(): Long = TASK_INTERVAL

    @Provides
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) }
        )
        .build()

    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        BASE_URL: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun providesCurrencyApi(retrofit: Retrofit): CurrencyApi = retrofit.create(CurrencyApi::class.java)
}
