package com.sorokin.yamob.cashmaster.ui.transaction_new

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.delegateadapter.delegate.diff.IComparableItem

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.ui.adapter.MyDiffUtilCompositeAdapter
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.ui.transaction_new.delegate_adapter.*
import com.sorokin.yamob.cashmaster.util.observe
import kotlinx.android.synthetic.main.fragment_new_transaction.*
import timber.log.Timber

class TransactionNewFragment : BaseActivityFragment<TransactionNewViewModel>() {
    companion object {
        fun newInstance() = TransactionNewFragment()
    }

    lateinit var mainRVAdapter: MyDiffUtilCompositeAdapter
    lateinit var layoutManager: GridLayoutManager

    var data = mutableListOf<IComparableItem>()

    override fun provideViewModel(): TransactionNewViewModel
            = ViewModelProviders.of(this, viewModelFactory)[TransactionNewViewModel::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_transaction, container, false)
    }

    fun updateDataInRv(){
        data.clear()

        data.add(TextItem("Accounts"))
        viewModel.wallets.value?.forEachIndexed { _, tp ->
            data.add(WalletItem(tp))
        }

        data.add(TextItem("Income targets"))
        viewModel.targets.value?.takeWhile { it.transactionType == MoneyTransaction.INCOME }?.forEachIndexed { _, tp ->
            data.add(TargetItem(tp))
        }

        data.add(TextItem("Expense targets"))
        viewModel.targets.value?.dropWhile { it.transactionType == MoneyTransaction.INCOME }?.forEachIndexed { _, tp ->
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

        layoutManager = rv_main.layoutManager as GridLayoutManager

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(mainRVAdapter.getItem(position)){
                    is TextItem -> layoutManager.spanCount
                    else -> 1
                }
            }

        }
        rv_main.layoutManager = layoutManager

        rv_main.setOnDragListener { _, dragEvent ->
            val selectedView = dragEvent.localState as View

            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    Timber.i("RECYCLER DRAG STARTED")
                    if(selectedView.tag is TargetItem){
                        Timber.i("Smooth scrolling")
                        rv_main.isLayoutFrozen = false
                        rv_main.smoothScrollToPosition(0)
                        return@setOnDragListener false
                    }
                }
            }
            return@setOnDragListener true
        }

        mainRVAdapter = MyDiffUtilCompositeAdapter.Builder()
                .add(TextDelegateAdapter())
                .add(TargetDelegateAdapter())
                .add(WalletDelegateAdapter())
                .build()

        rv_main.adapter = mainRVAdapter

    }

}
