package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class HomeViewModel @Inject constructor(resources: Resources) : BaseFragmentViewModel(resources) {
    override val toolbarIsVisible = mutableLiveDataWithVal(true)
    override val fabIsVisible = mutableLiveDataWithVal(false)

    val categories = mutableLiveDataWithVal(listOf("Car", "Food", "House", "Medicine", "Car 1", "Food 1", "House 1", "Medicine 1", "Car 2", "Food 2", "House 2", "Medicine 2",
            "Car 3", "Food 3", "House 3", "Medicine 3"))
}
