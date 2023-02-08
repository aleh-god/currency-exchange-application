package by.godevelopment.currencyexchangeapplication.di

import by.godevelopment.currencyexchangeapplication.data.datasources.CurrencyRemoteDataSourceImpl
import by.godevelopment.currencyexchangeapplication.data.interfaces.CurrencyApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://us-central1-epam-laba-13-1527598553135.cloudfunctions.net/myWebsiteBackend/api/"
private const val REFRESH_RATE_TASK_INTERVAL = 5000L

@Module
class NetworkModule {

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
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi = retrofit.create(
        CurrencyApi::class.java
    )

    @Provides
    @Singleton
    fun provideCurrencyDataSource(
        currencyApi: CurrencyApi,
        @IoDispatcher
        ioDispatcher: CoroutineDispatcher
    ): CurrencyRemoteDataSourceImpl = CurrencyRemoteDataSourceImpl(
        currencyApi = currencyApi,
        ioDispatcher = ioDispatcher,
        refreshIntervalMs = REFRESH_RATE_TASK_INTERVAL
    )
}
