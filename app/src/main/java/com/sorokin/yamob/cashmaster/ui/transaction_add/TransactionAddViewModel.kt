package com.sorokin.yamob.cashmaster.ui.transaction_add

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.entity.MockData
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionTarget
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class TransactionAddViewModel @Inject constructor(resources: Resources
) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(false)
    override val fabIsVisible = mutableLiveDataWithVal(false)
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

    val transactionType = MutableLiveData<Int>()
    val walletFrom = MutableLiveData<Wallet>()
    val walletTo = MutableLiveData<Wallet>()
    val targetFrom = MutableLiveData<MoneyTransactionTarget>()
    val targetTo = MutableLiveData<MoneyTransactionTarget>()

    val currencies = mutableLiveDataWithVal(MockData.currencies)


    fun setTransactionType(tt: Int){
        transactionType.value = tt
    }
    fun setWalletFromId(id: Int){
        walletFrom.value = MockData.wallets.first { it.id == id }
    }
    fun setWalletToId(id: Int){
        walletTo.value = MockData.wallets.first { it.id == id }
    }
    fun setTargetToId(id: Int){
        targetTo.value = MockData.targets.first { it.id == id }
    }
    fun setTargetFromId(id: Int){
        targetFrom.value = MockData.targets.first { it.id == id }
    }

}