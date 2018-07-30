package com.sorokin.yamob.cashmaster.ui.transaction_new.delegate_adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.sorokin.yamob.cashmaster.R

class DividerItem(): IComparableItem{

    override fun id(): Any = 0

    override fun content(): Any = 0

}

class DividerDelegateAdapter : KDelegateAdapter<DividerItem>() {
    override fun getLayoutId(): Int = R.layout.item_line_divider

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is DividerItem

    override fun onBind(item: DividerItem, viewHolder: KViewHolder) = with(viewHolder) {
        itemView.tag = item
    }

}