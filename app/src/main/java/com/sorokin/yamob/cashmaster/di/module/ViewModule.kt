package com.sorokin.yamob.cashmaster.di.module

import com.sorokin.yamob.cashmaster.ui.about.AboutFragment
import com.sorokin.yamob.cashmaster.ui.transaction_new.TransactionNewFragment
import com.sorokin.yamob.cashmaster.ui.transaction_add.TransactionAddFragment
import com.sorokin.yamob.cashmaster.ui.main.MainActivity
import com.sorokin.yamob.cashmaster.ui.settings.SettingsFragment
import com.sorokin.yamob.cashmaster.ui.transactions_show.TransactionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun transactionsFragment(): TransactionsFragment

    @ContributesAndroidInjector
    abstract fun transactionNewFragment(): TransactionNewFragment

    @ContributesAndroidInjector
    abstract fun transactionAddFragment(): TransactionAddFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun aboutFragment(): AboutFragment
}