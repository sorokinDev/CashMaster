package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.sorokin.yamob.mycash.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewModel>: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: T

    inline fun <reified T : ViewModel> getViewModel(
            viewModelFactory: ViewModelProvider.Factory
    ): T = ViewModelProviders.of(this, viewModelFactory)[T::class.java]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = provideViewModel()
    }

    abstract fun provideViewModel(): T
}