package com.sorokin.yamob.cashmaster.ui.settings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class SettingsViewModel @Inject constructor(resources: Resources) : BaseFragmentViewModel(resources) {
    override val fabIsVisible = mutableLiveDataWithVal(false)
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)
}