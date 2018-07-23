package com.sorokin.yamob.cashmaster.ui.about

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseFragmentViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AboutViewModel @Inject constructor(router: Router, resources: Resources) : BaseFragmentViewModel(router, resources) {
    override val fabIsVisible = mutableLiveDataWithVal(false)
    override val toolbarTitle: MutableLiveData<String> = mutableLiveDataWithVal(resources.getString(R.string.fragment_about_title))
    override val toolbarIsVisible: LiveData<Boolean> = mutableLiveDataWithVal(true)

}
