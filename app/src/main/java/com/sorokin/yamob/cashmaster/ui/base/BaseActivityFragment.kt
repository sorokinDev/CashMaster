package com.sorokin.yamob.cashmaster.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.sorokin.yamob.cashmaster.ui.shared.SharedViewModel
import com.sorokin.yamob.cashmaster.util.observe
import com.sorokin.yamob.mycash.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseActivityFragment<T: BaseFragmentViewModel>: BaseFragment<T>() {

    lateinit var sharedViewModel: SharedViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(this.activity!!, viewModelFactory)[SharedViewModel::class.java]

        viewModel.toolbarIsVisible.observe(this, { sharedViewModel.setToolbarIsVisible(it) })
        viewModel.fabIsVisible.observe(this, { sharedViewModel.setFabIsVisible(it) })
        viewModel.bottomNavigationIsVisible.observe(this, { sharedViewModel.setBottomNavigationIsVisible(it) })

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel()
    }

}
