package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseFragmentViewModel(
        router: Router,
        resources: Resources
) : BaseViewModel(router, resources) {
    abstract val toolbarTitle: MutableLiveData<String>
    abstract val toolbarIsVisible: LiveData<Boolean>
    abstract val fabIsVisible: MutableLiveData<Boolean>

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun setToolbarTitleAndVisibility(){

    }
}

