package com.sorokin.yamob.cashmaster.di.module

import com.sorokin.yamob.cashmaster.data.source.remote.api.CurrencyApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    companion object {
        private const val CURRENCY_API_BASE_URL = "https://free.currencyconverterapi.com"
    }

    val okhttpclient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideCurrencyApi(): CurrencyApi {
        return Retrofit.Builder()
                .baseUrl(CURRENCY_API_BASE_URL)
                .callFactory(okhttpclient.newBuilder().build())
                .build()
                .create(CurrencyApi::class.java)
    }
}