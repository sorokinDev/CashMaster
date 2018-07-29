package com.sorokin.yamob.cashmaster.ui.home.delegate_adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.sorokin.yamob.cashmaster.R
import kotlinx.android.synthetic.main.item_simple_text.*

class TextItem(var title: String): IComparableItem{

    override fun id(): Any = title

    override fun content(): Any = title

}

class TextDelegateAdapter : KDelegateAdapter<TextItem>() {
    override fun getLayoutId(): Int = R.layout.item_simple_text

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is TextItem

    override fun onBind(item: TextItem, viewHolder: KViewHolder) = with(viewHolder) {
        itemView.tag = item
        tv_title.text = item.title
    }

}