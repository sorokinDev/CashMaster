package com.sorokin.yamob.cashmaster.ui.transaction_new

import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionTarget
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class TransactionNewViewModel @Inject constructor(resources: Resources) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible = mutableLiveDataWithVal(true)
    override val fabIsVisible = mutableLiveDataWithVal(false)

    val wallets = mutableLiveDataWithVal(listOf(
            Wallet(0, "Cash", 1000.0, "RUB", Wallet.CASH),
            Wallet(1, "Tinkoff",32100.0, "USD", Wallet.BANK_CARD),
            Wallet(2, "Bank", 456321.0, "EUR", Wallet.BANK_ACCOUNT)))

    val targets = mutableLiveDataWithVal(listOf(
            MoneyTransactionTarget(0, "JOB", MoneyTransaction.INCOME),
            MoneyTransactionTarget(1, "Freelance", MoneyTransaction.INCOME),
            MoneyTransactionTarget(2, "Tutoring", MoneyTransaction.INCOME),
            MoneyTransactionTarget(3, "Clothes", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(4, "Auto", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(5, "Medicine", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(6, "House", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(7, "Weekend", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(8, "Food", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(9, "Gifts", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(10, "Travelling", MoneyTransaction.EXPENSE)

    ))
}
