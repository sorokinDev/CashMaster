package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T: ViewModel>: DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    lateinit var viewModel: T

    inline fun <reified T : ViewModel> getViewModel(
            viewModelFactory: ViewModelProvider.Factory
    ): T = ViewModelProviders.of(this, viewModelFactory)[T::class.java]


    abstract fun provideViewModel(): T
}