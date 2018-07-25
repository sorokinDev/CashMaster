package com.sorokin.yamob.cashmaster.data.entity

import android.icu.util.CurrencyAmount
import java.util.*



class MoneyTransaction(
        val amount: Double,
        val currency: String,
        val date: Date,
        val type: MoneyTransaction.Type
){
    enum class Type{
        INCOMING, EXPENSE
    }
}