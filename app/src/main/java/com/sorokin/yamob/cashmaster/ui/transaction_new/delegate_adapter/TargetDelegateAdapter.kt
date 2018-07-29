package com.sorokin.yamob.cashmaster.ui.transaction_new.delegate_adapter

import android.content.ClipData
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.view.DragEvent
import android.view.View
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionTarget
import kotlinx.android.synthetic.main.item_category.*
import timber.log.Timber



class TargetItem(var target: MoneyTransactionTarget): IComparableItem {

    override fun id(): Any = target.id

    override fun content(): Any = target.id.toString() + target.name

}

class TargetDelegateAdapter : KDelegateAdapter<TargetItem>() {
    override fun getLayoutId(): Int = R.layout.item_category

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is TargetItem

    override fun onBind(item: TargetItem, viewHolder: KViewHolder) = with(viewHolder) {
        Timber.i("BIND: ${item.target.name}")
        itemView.tag = item
        viewHolder.tv_target_title.text = item.target.name

        if(item.target.transactionType == MoneyTransaction.EXPENSE){
            tv_target_title.setTextColor(ResourcesCompat.getColor(itemView.resources, R.color.colorExpense, null))
        }else{
            tv_target_title.setTextColor(ResourcesCompat.getColor(itemView.resources, R.color.colorIncome , null))
        }

        itemView.setOnLongClickListener {
            Timber.i("ACTION LONG CLICK")
            val clipData = ClipData.newPlainText("id", item.target.id.toString())
            val shadowBuilder = View.DragShadowBuilder(itemView)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.startDragAndDrop(clipData, shadowBuilder, itemView, 0)
            }else{
                itemView.startDrag(clipData, shadowBuilder, itemView, 0)
            }
            itemView.post { itemView.alpha = 0.5f }
            return@setOnLongClickListener true
        }

        itemView.setOnDragListener { _, dragEvent ->
            val selectedView = dragEvent.localState as View

            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if(selectedView == itemView){
                        return@setOnDragListener true
                    }else if(selectedView.tag is TargetItem){
                        return@setOnDragListener false
                    }
                }
                DragEvent.ACTION_DRAG_LOCATION -> {

                }
                DragEvent.ACTION_DROP -> {

                    return@setOnDragListener false
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    selectedView.post { selectedView.alpha = 1f }
                }
                else -> { }
            }
            return@setOnDragListener true
        }
    }

}