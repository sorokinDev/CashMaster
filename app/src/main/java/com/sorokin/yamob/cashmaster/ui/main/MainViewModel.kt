package com.sorokin.yamob.cashmaster.ui.main

import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class MainViewModel @Inject constructor(resources: Resources) : BaseViewModel(resources) {
    var homeAsUpEnabled = mutableLiveDataWithVal(false)
}