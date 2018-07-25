package com.sorokin.yamob.cashmaster.ui.shared

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import android.support.annotation.StringRes
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class SharedViewModel @Inject constructor(
        val resources: Resources
): ViewModel() {
    val toolbarIsVisible = mutableLiveDataWithVal(true)
    val fabIsVisible = mutableLiveDataWithVal(false)

    fun setToolbarIsVisible(vis: Boolean){
        toolbarIsVisible.value = vis
    }

    fun setFabIsVisible(vis: Boolean){
        fabIsVisible.value = vis
    }


}