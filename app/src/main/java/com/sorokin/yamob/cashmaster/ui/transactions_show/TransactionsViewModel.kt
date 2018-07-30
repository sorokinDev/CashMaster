package com.sorokin.yamob.cashmaster.ui.transactions_show

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.entity.MockData
import com.sorokin.yamob.cashmaster.data.entity.Resource
import com.sorokin.yamob.cashmaster.data.interactor.WalletInteractor
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject


class TransactionsViewModel @Inject constructor(resources: Resources, val interactor: WalletInteractor) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible = mutableLiveDataWithVal(true)
    override val fabIsVisible = mutableLiveDataWithVal(false)

    val currencies = mutableLiveDataWithVal(MockData.currencies)
    val wallets = mutableLiveDataWithVal(MockData.wallets)
    val transactions = mutableLiveDataWithVal(MockData.transactions)

    val selectedWallet = mutableLiveDataWithVal(0)
    val selectedCurrencyForWallets = mutableLiveDataWithVal(MockData.wallets.map { it.currency }.toMutableList())

    fun getExchangeRate(fromCur: String, toCur: String): LiveData<Resource<Double>> {
        return interactor.getExchangeRate(fromCur, toCur)
    }
}
