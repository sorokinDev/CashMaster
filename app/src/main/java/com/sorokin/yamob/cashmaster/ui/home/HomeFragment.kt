package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment

class HomeFragment : BaseActivityFragment<HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun provideViewModel(): HomeViewModel
            = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
