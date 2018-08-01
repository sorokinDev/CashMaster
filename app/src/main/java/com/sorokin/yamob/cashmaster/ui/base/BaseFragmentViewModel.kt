package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

abstract class BaseFragmentViewModel(
        resources: Resources
) : BaseViewModel(resources) {
    abstract val toolbarIsVisible: LiveData<Boolean>
    abstract val fabIsVisible: MutableLiveData<Boolean>
    abstract val bottomNavigationIsVisible: MutableLiveData<Boolean>
}

