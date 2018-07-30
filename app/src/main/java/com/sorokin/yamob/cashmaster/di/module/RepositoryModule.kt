package com.sorokin.yamob.cashmaster.di.module

import com.sorokin.yamob.cashmaster.data.interactor.WalletInteractor
import com.sorokin.yamob.cashmaster.data.repository.CurrencyRepository
import com.sorokin.yamob.cashmaster.data.source.remote.api.CurrencyApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyApi: CurrencyApi): CurrencyRepository = CurrencyRepository(currencyApi)

    @Provides
    @Singleton
    fun provideWalletInteractor(currencyRepository: CurrencyRepository): WalletInteractor = WalletInteractor(currencyRepository)

}