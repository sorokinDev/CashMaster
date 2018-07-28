package com.sorokin.yamob.cashmaster.ui.home.delegate_adapter

import android.content.ClipData
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.LinearLayout
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.sorokin.yamob.cashmaster.R
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_simple_text.*
import timber.log.Timber



class CategoryItem(var id: Int, var title: String): IComparableItem {

    override fun id(): Any = id

    override fun content(): Any = id.toString() + title

}

class CategoryDelegateAdapter : KDelegateAdapter<CategoryItem>() {
    override fun getLayoutId(): Int = R.layout.item_simple_text

    override fun isForViewType(items: MutableList<*>, pos: Int): Boolean = items[pos] is CategoryItem

    override fun onBind(item: CategoryItem, viewHolder: KViewHolder) = with(viewHolder) {
        Timber.i("BIND: ${item.title}")
        itemView.tag = item.id
        viewHolder.tv_title.text = item.title
        itemView.setOnLongClickListener {
            Timber.i("ACTION LONG CLICK")
            val clipData = ClipData.newPlainText("id", item.id.toString())
            val shadowBuilder = View.DragShadowBuilder(itemView)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.startDragAndDrop(clipData, shadowBuilder, itemView, 0)
            }else{
                itemView.startDrag(clipData, shadowBuilder, itemView, 0)
            }
            itemView.background = ContextCompat.getDrawable(itemView.context, android.R.color.holo_red_dark)
            return@setOnLongClickListener true
        }

        itemView.setOnDragListener { v, dragEvent ->
            val selectedView = dragEvent.localState as View
            val rvSelected = v as LinearLayout

            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    val onTopOf = v
                    Timber.i("DRAG LOCATION ON TOP OF ${onTopOf.tag}")
                    //newContactPosition = rcvSelected.getChildAdapterPosition(onTopOf)
                }
                DragEvent.ACTION_DRAG_ENTERED -> {

                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    Timber.i("DRAG_EXITED")
                }
                DragEvent.ACTION_DROP -> {
                    Timber.i("ACTION DROP")
                    selectedView.background = ContextCompat.getDrawable(itemView.context, android.R.color.holo_blue_dark)
//                    if (isFromExercise) {
//                        exerciseSelectedList.add(exerciseToMove)
//                        exerciseList.remove(exerciseToMove)
//                        mainActivityBinding.rcvChooseExercise.getAdapter().notifyItemRemoved(currentPosition)
//                        mainActivityBinding.executePendingBindings()
//                    }
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    Timber.i("DRAG ENDED")
                    //selectedView.visibility = View.VISIBLE
                    // Boundary condition, scroll to top is moving list item to position 0.
//                    if (newContactPosition !== -1) {
//                        rcvSelected.scrollToPosition(newContactPosition)
//                        newContactPosition = -1
//                    } else {
//                        rcvSelected.scrollToPosition(0)
//                    }
                }
                else -> { }
            }
            return@setOnDragListener true
        }
    }

}