package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.data.repository.WalletRepository
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HomeViewModel @Inject constructor(router: Router,
                                        resources: Resources,
                                        var walletRepository: WalletRepository
) : BaseFragmentViewModel(router, resources) {
    override val fabIsVisible = mutableLiveDataWithVal(true)
    override val toolbarTitle: MutableLiveData<String> = mutableLiveDataWithVal(resources.getString(R.string.fragment_home_title))
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

    var currentCurrency = mutableLiveDataWithVal("RUB")
    var walletInCurrentCurrency = mutableLiveDataWithVal(walletRepository.getWallet())

    fun setCurrentCurrency(currency: String) {
        currentCurrency.value = currency
        walletInCurrentCurrency.value = walletRepository.getWalletInCurrency(currency)
    }


}