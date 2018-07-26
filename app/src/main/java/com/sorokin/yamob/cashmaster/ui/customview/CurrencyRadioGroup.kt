package com.sorokin.yamob.cashmaster.ui.customview

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.sorokin.yamob.cashmaster.R
import java.util.*
import android.content.res.TypedArray



class CurrencyRadioGroup: RadioGroup {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs){
        attr = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CurrencyRadioGroup,0, 0)
    }

    private lateinit var favoriteCurrencies: Array<Currency>
    private var showOtherCurrencies: Boolean = false
    private lateinit var allCurrencies: Array<Currency>
    private lateinit var attr: TypedArray

    /**
     * Sets data to the view
     *
     * @param[favoriteCurrencies] Main currencies, that will be shown in radiobuttons
     * @param[showOtherCurrencies] Show button with other currencies?
     * @param[allCurrencies] Currencies that will be shown in other currencies spinner
     */
    fun setCurrencies(favoriteCurrencies: Array<Currency>, showOtherCurrencies: Boolean,
                      allCurrencies: Array<Currency>? = null){
        this.favoriteCurrencies = favoriteCurrencies
        this.showOtherCurrencies = showOtherCurrencies
        this.allCurrencies = allCurrencies ?: arrayOf()
        this.removeAllViews()


        for(cur in favoriteCurrencies){
            var rbWidth: Int
            var rbHeight: Int
            if(this.orientation == LinearLayout.HORIZONTAL){
                rbWidth = LayoutParams.MATCH_PARENT
                rbHeight = LayoutParams.WRAP_CONTENT
            }else{
                rbWidth = LayoutParams.WRAP_CONTENT
                rbHeight = LayoutParams.MATCH_PARENT
            }
            val llParams = LinearLayout.LayoutParams(rbWidth, rbHeight, 1f)

            var rb = RadioButton(context)
            rb.layoutParams = llParams
            rb.textAlignment = View.TEXT_ALIGNMENT_CENTER
            rb.text = cur.currencyCode
            rb.buttonDrawable = null
            rb.background = ResourcesCompat.getDrawable(resources, R.drawable.rb_currency_background, context.theme)
            rb.setTextColor(attr.getColor(R.styleable.CurrencyRadioGroup_crg_rb_textColor, android.R.color.black))
            addView(rb)
        }

        invalidate()
    }


}