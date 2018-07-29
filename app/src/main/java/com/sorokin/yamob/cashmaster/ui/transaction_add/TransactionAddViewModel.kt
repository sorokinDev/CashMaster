package com.sorokin.yamob.cashmaster.ui.transaction_add

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionTarget
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class TransactionAddViewModel @Inject constructor(resources: Resources,
                                                  private val walletRepository: WalletRepository
) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(false)
    override val fabIsVisible = mutableLiveDataWithVal(false)
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

    val transactionType = MutableLiveData<Int>()
    val walletFrom = MutableLiveData<Wallet>()
    val walletTo = MutableLiveData<Wallet>()
    val targetFrom = MutableLiveData<MoneyTransactionTarget>()
    val targetTo = MutableLiveData<MoneyTransactionTarget>()


    fun setTransactionType(tt: Int){
        transactionType.value = tt
    }
    fun setWalletFromId(id: Int){
        walletFrom.value = Wallet(1, "Cash", 1000.0, "RUB", Wallet.CASH)
    }
    fun setWalletToId(id: Int){
        walletTo.value = Wallet(2, "Sberbank", 1000.0, "RUB", Wallet.BANK_CARD)
    }
    fun setTargetToId(id: Int){
        targetTo.value = MoneyTransactionTarget(1, "Car", MoneyTransaction.EXPENSE)
    }
    fun setTargetFromId(id: Int){
        targetFrom.value = MoneyTransactionTarget(2, "Job", MoneyTransaction.INCOME)
    }

}