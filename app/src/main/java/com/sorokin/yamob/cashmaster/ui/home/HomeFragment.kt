package com.sorokin.yamob.cashmaster.ui.home


import android.content.Context
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
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.ViewParent
import kotlinx.android.synthetic.main.item_account.view.*
import java.util.*


class HomeFragment : BaseActivityFragment<HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vp_accounts.adapter = AccountsVPAdapter()
        vp_accounts_indicator.setupWithViewPager(vp_accounts, true)
    }

    override fun provideViewModel(): HomeViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    class AccountsVPAdapter() : PagerAdapter() {
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            (container as ViewPager).removeView(obj as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = container.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val itemView = inflater.inflate(R.layout.item_account, container, false)

            itemView.rg_currencies.setCurrencies(arrayOf(Currency.getInstance("RUB"), Currency.getInstance("USD")), false)

            itemView.tv_account_balance.text = position.toString() + " RUB"
            itemView.tv_account_name.text = "Account name"

            container.addView(itemView)

            return itemView
        }


        override fun getCount(): Int = 3

        override fun isViewFromObject(view: View, obj: Any) = view == obj


    }


}
