package com.sorokin.yamob.cashmaster.di.module

import com.sorokin.yamob.cashmaster.data.repository.WalletRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideWalletRepository(): WalletRepository = WalletRepository()

}