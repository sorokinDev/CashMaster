package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import com.sorokin.yamob.mycash.util.ViewModelKey
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseViewModel constructor(
        val router: Router,
        val resources: Resources
): ViewModel() {

}