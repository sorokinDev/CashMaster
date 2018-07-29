package com.sorokin.yamob.cashmaster.ui.transactions_show

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(resources: Resources) : BaseFragmentViewModel(resources) {
    override val bottomNavigationIsVisible = mutableLiveDataWithVal(true)
    override val toolbarIsVisible = mutableLiveDataWithVal(true)
    override val fabIsVisible = mutableLiveDataWithVal(false)

}
