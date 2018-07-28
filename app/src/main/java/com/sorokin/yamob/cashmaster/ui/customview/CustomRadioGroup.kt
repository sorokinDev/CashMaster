package com.sorokin.yamob.cashmaster.ui.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.util.dpToPx
import android.graphics.Typeface
import android.os.Parcelable
import android.util.TypedValue
import com.sorokin.yamob.cashmaster.util.pxToSp
import com.sorokin.yamob.cashmaster.util.spToPx
import kotlinx.android.parcel.Parcelize


class CustomRadioGroup: RadioGroup{
    override fun onRestoreInstanceState(state: Parcelable?) {
        if(state is SavedState){
            checkedItem = (state).checkedItem
            super.onRestoreInstanceState(state.superState)
        }else{
            super.onRestoreInstanceState(state)
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState(), checkedItem)
    }

    @Parcelize
    data class SavedState(val sState: Parcelable, val checkedItem: Int = 0): BaseSavedState(sState)

    var checkedItem: Int = 0
    var allItems = listOf<String>()
    var mainItems = listOf<String>()
    var additionalItems = listOf<String>()
    var itemKeys = hashMapOf<String, Int>()

    lateinit var onSelectedItemChanged: (Int) -> Unit

    private lateinit var attr: TypedArray

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        attr = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomRadioGroup,0, 0)
    }


    fun setItems(items: List<String>, mainCnt: Int = items.size){
        allItems = items
        mainItems = items.take(mainCnt)
        additionalItems = items.drop(mainCnt)

        itemKeys.clear()
        allItems.forEachIndexed { idx, str -> itemKeys[str] = idx }

        removeAllViews()

        val (rbWidth, rbHeight) = getRBWidthHeight()

        mainItems.forEachIndexed { idx, itm ->
            val llParams = LinearLayout.LayoutParams(rbWidth, rbHeight, 1f)

            val rb = RadioButton(context)
            rb.layoutParams = llParams
            rb.textAlignment = View.TEXT_ALIGNMENT_CENTER
            rb.text = itm
            rb.buttonDrawable = null
            rb.setTextColor(attr.getColorStateList(R.styleable.CustomRadioGroup_crg_rb_textColor))
            rb.textAlignment = View.TEXT_ALIGNMENT_CENTER
            rb.setTextSize(TypedValue.COMPLEX_UNIT_PX, attr.getDimensionPixelSize(R.styleable.CustomRadioGroup_crg_rb_textSize, 14.spToPx(context)).toFloat())

            val pad = 16.dpToPx(context)
            rb.setPadding(pad, pad, pad, pad)

            rb.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    checkedItem = idx
                    buttonView.typeface = Typeface.DEFAULT_BOLD
                    rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, (rb.textSize.toInt().pxToSp(context) + 2).toFloat())
                    onSelectedItemChanged(idx)
                }else{
                    buttonView.typeface = Typeface.DEFAULT
                    rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, rb.textSize.toInt().pxToSp(context).toFloat())
                }
            }

            addView(rb)
            rb.isChecked = idx == checkedItem
        }

        invalidate()
    }



    fun getRBWidthHeight(): Pair<Int, Int>{
        return if(orientation == LinearLayout.HORIZONTAL){
            Pair(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }else{
            Pair(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
    }


}
