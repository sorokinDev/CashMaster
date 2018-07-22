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
    val title= mutableLiveDataWithVal(resources.getString(R.string.app_name))
    val toolbarIsVisible = mutableLiveDataWithVal(true)

    fun setToolbarIsVisible(vis: Boolean){
        toolbarIsVisible.value = vis
    }

    fun setToolbarTitle(newTitle: String){
        title.value = newTitle
    }

    fun setToolbarTitle(@StringRes newTitle: Int){
        title.value = resources.getString(newTitle)
    }


}