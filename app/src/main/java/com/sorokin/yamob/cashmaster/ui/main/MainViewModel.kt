package com.sorokin.yamob.cashmaster.ui.main

import android.content.res.Resources
import com.sorokin.yamob.cashmaster.ui.base.BaseViewModel
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(router: Router, resources: Resources) : BaseViewModel(router, resources) {
    var homeAsUpEnabled = mutableLiveDataWithVal(false)
}