package com.sorokin.yamob.cashmaster.ui.about


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment

class AboutFragment : BaseActivityFragment<AboutViewModel>() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun provideViewModel(): AboutViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_about, container, false)
    }


}
