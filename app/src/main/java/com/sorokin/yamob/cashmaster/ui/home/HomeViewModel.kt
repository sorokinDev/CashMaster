package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionTarget
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class HomeViewModel @Inject constructor(resources: Resources) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible = mutableLiveDataWithVal(true)
    override val fabIsVisible = mutableLiveDataWithVal(false)

    val wallets = mutableLiveDataWithVal(listOf(
            Wallet(0, "Cash", 1000.0, "RUB", Wallet.Type.CASH),
            Wallet(1, "Tinkoff",32100.0, "USD", Wallet.Type.BANK_CARD),
            Wallet(2, "Bank", 456321.0, "EUR", Wallet.Type.BANK_ACCOUNT)))

    val targets = mutableLiveDataWithVal(listOf(
            MoneyTransactionTarget(0, "JOB", MoneyTransaction.Type.INCOMING),
            MoneyTransactionTarget(1, "Freelance", MoneyTransaction.Type.INCOMING),
            MoneyTransactionTarget(2, "Tutoring", MoneyTransaction.Type.INCOMING),
            MoneyTransactionTarget(3, "Clothes", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(4, "Auto", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(5, "Medicine", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(6, "House", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(7, "Weekend", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(8, "Food", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(9, "Gifts", MoneyTransaction.Type.EXPENSE),
            MoneyTransactionTarget(10, "Travelling", MoneyTransaction.Type.EXPENSE)

    ))
}
