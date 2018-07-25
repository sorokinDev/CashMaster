package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.repository.WalletRepository
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class HomeViewModel @Inject constructor(resources: Resources,
                                        private val walletRepository: WalletRepository
) : BaseFragmentViewModel(resources) {
    override val fabIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

    val currentCurrency = mutableLiveDataWithVal("RUB")
    val walletInCurrentCurrency = mutableLiveDataWithVal(walletRepository.getWallet())

    fun setCurrentCurrency(currency: String) {
        currentCurrency.value = currency
        walletInCurrentCurrency.value = walletRepository.getWalletInCurrency(currency)
    }


}