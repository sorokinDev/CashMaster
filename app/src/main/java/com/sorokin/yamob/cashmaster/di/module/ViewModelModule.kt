package com.sorokin.yamob.cashmaster.di.module

import android.arch.lifecycle.ViewModel
import com.sorokin.yamob.cashmaster.ui.about.AboutViewModel
import com.sorokin.yamob.cashmaster.ui.transaction_new.TransactionNewViewModel
import com.sorokin.yamob.cashmaster.ui.transaction_add.TransactionAddViewModel
import com.sorokin.yamob.cashmaster.ui.main.MainViewModel
import com.sorokin.yamob.cashmaster.ui.settings.SettingsViewModel
import com.sorokin.yamob.cashmaster.ui.shared.SharedViewModel
import com.sorokin.yamob.cashmaster.ui.transactions_show.TransactionsViewModel
import com.sorokin.yamob.mycash.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel : MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(viewModel : SharedViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    abstract fun bindTransactionsViewModel(viewModel : TransactionsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionNewViewModel::class)
    abstract fun bindTransactionNewViewModel(viewModel : TransactionNewViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionAddViewModel::class)
    abstract fun bindTransactionAddViewModel(viewModel : TransactionAddViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel : SettingsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(viewModel : AboutViewModel) : ViewModel
}