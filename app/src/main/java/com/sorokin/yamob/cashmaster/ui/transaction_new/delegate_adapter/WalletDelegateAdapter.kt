package com.sorokin.yamob.cashmaster.ui.transaction_new.delegate_adapter

import android.annotation.SuppressLint
import android.view.DragEvent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import com.sorokin.yamob.cashmaster.ui.transaction_new.TransactionNewFragmentDirections

import kotlinx.android.synthetic.main.item_wallet.*
import timber.log.Timber
import java.text.DecimalFormat
import java.util.*

class WalletItem(var wallet: Wallet): IComparableItem{

    override fun id(): Any = wallet.id

    override fun content(): Any = wallet.id.toString() + wallet.currency + wallet.money

}

class WalletDelegateAdapter : KDelegateAdapter<WalletItem>() {
    val numberFormatter = DecimalFormat("#,###")

    override fun getLayoutId(): Int = R.layout.item_wallet

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is WalletItem

    @SuppressLint("SetTextI18n")
    override fun onBind(item: WalletItem, viewHolder: KViewHolder) = with(viewHolder) {
        itemView.tag = item
        tv_wallet_title.text = item.wallet.name
        // TODO: REFACTOR Number formatter, Currency symbol
        tv_wallet_balance.text = numberFormatter.format(item.wallet.money.toInt()).replace(',', ' ') +
                when(item.wallet.currency){
                    "RUB" -> "\u20BD"
                    "USD" -> "$"
                    "EUR" -> "€"
                    else -> ""
                }
        viewHolder.iv_wallet_icon.setImageResource(item.wallet.drawable)
        itemView.setOnDragListener { v, dragEvent ->
            val selectedView = dragEvent.localState as View

            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    if(v == itemView){
                        v.post { v.alpha = 0.6f }
                    }
                }
                DragEvent.ACTION_DRAG_STARTED -> {
                    v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.anim_shake))
                    return@setOnDragListener true
                }
                DragEvent.ACTION_DROP -> {
                    Timber.i(" WALLET ACTION DROP ${v.tag}")
                    if(v.tag is WalletItem){
                        val targetItem = selectedView.tag as TargetItem
                        val walItem = v.tag as WalletItem
                        val nav_act = TransactionNewFragmentDirections.NavActionAddTransaction()
                        nav_act.setTransactionType(targetItem.target.transactionType)
                        if(targetItem.target.transactionType == MoneyTransaction.INCOME){
                            nav_act.setWalletTo(walItem.wallet.id)
                            nav_act.setTarget(targetItem.target.id)
                        }else if(targetItem.target.transactionType == MoneyTransaction.EXPENSE){
                            nav_act.setWalletFrom(walItem.wallet.id)
                            nav_act.setTarget(targetItem.target.id)
                        }
                        Navigation.findNavController(v).navigate(nav_act)
                    }else{
                        return@setOnDragListener false
                    }

                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    Timber.i("WALLET DRAG ENDED")
                    if(selectedView.tag is TargetItem){
                        Timber.i("DRAG ENDED TARGET ITEM")
                        selectedView.post { selectedView.alpha = 1f }
                    }
                }
                else -> {
                    v.post { v.alpha = 1f }
                }
            }
            return@setOnDragListener true
        }
    }

}