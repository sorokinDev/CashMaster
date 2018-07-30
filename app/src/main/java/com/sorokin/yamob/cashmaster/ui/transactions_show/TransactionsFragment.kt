package com.sorokin.yamob.cashmaster.ui.transactions_show

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.Resource
import com.sorokin.yamob.cashmaster.ui.adapter.MyDiffUtilCompositeAdapter
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.util.asMoneyWithCur
import com.sorokin.yamob.cashmaster.util.observe
import kotlinx.android.synthetic.main.fragment_transactions.*
import kotlinx.android.synthetic.main.item_all_wallets.*
import kotlinx.android.synthetic.main.item_category.*
import kotlinx.android.synthetic.main.item_transaction.*
import kotlinx.android.synthetic.main.item_wallet_card.view.*

class TransactionsFragment : BaseActivityFragment<TransactionsViewModel>() {

    val walletsAdapter = AccountsVPAdapter()

    lateinit var rvAdapter: MyDiffUtilCompositeAdapter
    val rvData = mutableListOf<IComparableItem>()

    override fun provideViewModel(): TransactionsViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vp_wallets.adapter = walletsAdapter
        vp_wallets_indicator.setupWithViewPager(vp_wallets, true)

        rvAdapter = MyDiffUtilCompositeAdapter.Builder().add(TransactionDelegateAdapter()).build()

        rv_transactions.adapter = rvAdapter

        viewModel.wallets.observe(this){
            walletsAdapter.notifyDataSetChanged()
        }

        viewModel.transactions.observe(this){
            rvData.clear()
            it.forEach { tr -> rvData.add(TransactionItem(tr)) }
            rvAdapter.swapData(rvData)
            rvAdapter.notifyDataSetChanged()
        }

        viewModel.getExchangeRate("USD", "RUB").observe(this){
            when(it){
                is Resource.Loading -> Toast.makeText(requireContext(), "Loading USD rate", Toast.LENGTH_SHORT).show()
                is Resource.Failure -> Toast.makeText(requireContext(), "Can't load exchange rates", Toast.LENGTH_SHORT).show()
                is Resource.Success -> Toast.makeText(requireContext(), "USD / RUB : " + it.data, Toast.LENGTH_LONG).show()
            }
        }

    }

    inner class AccountsVPAdapter() : PagerAdapter() {
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            (container as ViewPager).removeView(obj as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = container.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val itemView = inflater.inflate(R.layout.item_wallet_card, container, false)

            val wal = viewModel.wallets.value?.get(position)

            itemView.tv_account_name.text = wal?.name ?: resources.getString(R.string.cash)
            itemView.tv_account_balance.text = wal?.money?.toInt()?.asMoneyWithCur(wal.currency) ?: 1000.asMoneyWithCur("RUB")
            itemView.rg_currencies.setItems(viewModel.currencies.value ?: listOf("RUB", "USD"))

            itemView.rg_currencies.onSelectedItemChanged = {
                if(wal?.currency != null){
                    viewModel.getExchangeRate(wal.currency, viewModel.currencies.value!![it]).observe(this@TransactionsFragment){res ->
                        when(res){
                            is Resource.Loading -> itemView.tv_account_balance.text = (wal.money * (res.data ?: 1.0)).toInt().asMoneyWithCur(viewModel.currencies.value!![it])
                            is Resource.Failure -> Toast.makeText(requireContext(), "Can't load exchange rates", Toast.LENGTH_SHORT).show()
                            is Resource.Success -> itemView.tv_account_balance.text = (wal.money * (res.data ?: 1.0)).toInt().asMoneyWithCur(viewModel.currencies.value!![it])
                        }
                    }
                }

            }

            container.addView(itemView)

            return itemView
        }


        override fun getCount(): Int = viewModel.wallets.value?.size ?: 0

        override fun isViewFromObject(view: View, obj: Any) = view == obj
    }

    class TransactionItem(var transaction: MoneyTransaction): IComparableItem {
        override fun id(): Any = transaction.id
        override fun content(): Any = transaction.id.toString() + transaction.amount
    }

    class TransactionDelegateAdapter : KDelegateAdapter<TransactionItem>() {

        override fun getLayoutId(): Int = R.layout.item_transaction
        override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is TransactionItem

        override fun onBind(item: TransactionItem, viewHolder: KViewHolder) = with(viewHolder) {
            itemView.tag = item

            iv_trans_target_icon.setImageResource(item.transaction.target.drawable)
            tv_trans_target.text = item.transaction.target.name
            tv_trans_sum.text = item.transaction.amount.toInt().asMoneyWithCur(item.transaction.currency)

            val colorId = when(item.transaction.type){
                MoneyTransaction.EXPENSE -> R.color.colorExpense
                else -> R.color.colorIncome
            }
            tv_trans_sum.setTextColor(ResourcesCompat.getColor(itemView.resources, colorId,null))
        }

    }

}
