package com.sorokin.yamob.cashmaster.ui.transaction_add

import android.arch.lifecycle.LiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.data.repository.WalletRepository
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class TransactionAddViewModel @Inject constructor(resources: Resources,
                                                  private val walletRepository: WalletRepository
) : BaseFragmentViewModel(resources) {
    override val fabIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

    val transactionTypes = mutableLiveDataWithVal(listOf("Expense", "Income"))
    val accounts = mutableLiveDataWithVal(listOf("Cash", "VISA", "Card 2"))
    val categories = mutableLiveDataWithVal(listOf("Car", "Food", "House", "Medicine", "Car 1", "Food 1", "House 1", "Medicine 1", "Car 2", "Food 2", "House 2", "Medicine 2",
            "Car 3", "Food 3", "House 3", "Medicine 3"))
    val currencies = mutableLiveDataWithVal(listOf("RUB", "USD", "EUR", "GBP"))


}