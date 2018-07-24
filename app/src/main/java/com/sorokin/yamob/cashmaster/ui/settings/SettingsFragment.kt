package com.sorokin.yamob.cashmaster.ui.settings


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.ui.home.HomeFragment
import com.sorokin.yamob.cashmaster.util.Screens
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseActivityFragment<SettingsViewModel>() {
    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_to_about.setOnClickListener {
            viewModel.router.navigateTo(Screens.ABOUT)
        }
    }

    override fun provideViewModel(): SettingsViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


}
