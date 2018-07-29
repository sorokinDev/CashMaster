package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.delegateadapter.delegate.diff.IComparableItem

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.ui.adapter.MyDiffUtilCompositeAdapter
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.ui.home.delegate_adapter.*
import com.sorokin.yamob.cashmaster.util.observe
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : BaseActivityFragment<HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var mainRVAdapter: MyDiffUtilCompositeAdapter
    lateinit var layoutManager: GridLayoutManager

    var data = mutableListOf<IComparableItem>()

    override fun provideViewModel(): HomeViewModel
            = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun updateDataInRv(){
        data.clear()

        data.add(TextItem("Accounts"))
        viewModel.wallets.value?.forEachIndexed { idx, tp ->
            data.add(WalletItem(tp))
        }

        data.add(TextItem("Income targets"))
        viewModel.targets.value?.takeWhile { it.transactionType == MoneyTransaction.Type.INCOMING }?.forEachIndexed { idx, tp ->
            data.add(TargetItem(tp))
        }

        data.add(TextItem("Expense targets"))
        viewModel.targets.value?.dropWhile { it.transactionType == MoneyTransaction.Type.INCOMING }?.forEachIndexed { idx, tp ->
            data.add(TargetItem(tp))
        }
        mainRVAdapter.swapData(data)
        mainRVAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.targets.observe(this){
            updateDataInRv()
        }
        viewModel.wallets.observe(this){
            updateDataInRv()
        }

        layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(mainRVAdapter.getItem(position)){
                    is TextItem -> layoutManager.spanCount
                    else -> 1
                }
            }

        }
        rv_main.layoutManager = layoutManager

        mainRVAdapter = MyDiffUtilCompositeAdapter.Builder()
                .add(TextDelegateAdapter())
                .add(TargetDelegateAdapter())
                .add(WalletDelegateAdapter())
                .build()

        rv_main.adapter = mainRVAdapter

    }

}
