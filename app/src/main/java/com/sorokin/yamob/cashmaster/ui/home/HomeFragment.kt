package com.sorokin.yamob.cashmaster.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.util.observe
import com.sorokin.yamob.cashmaster.util.toMoney
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseActivityFragment<HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rb_currency_0.text = "RUB"
        rb_currency_1.text = "USD"

        viewModel.walletInCurrentCurrency.observe(this, {
            tv_balance.text = it.money.toMoney().toString()
        })
        rg_currencies.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.rb_currency_0 -> viewModel.setCurrentCurrency("RUB")
                R.id.rb_currency_1 -> viewModel.setCurrentCurrency("USD")
            }
        }
        if(savedInstanceState == null){
            rg_currencies.check(R.id.rb_currency_0)
        }
    }

    override fun provideViewModel(): HomeViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
