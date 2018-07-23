package com.sorokin.yamob.cashmaster.data.entity

import android.icu.util.CurrencyAmount
import java.util.*



class MoneyTransaction(
        var amount: Double,
        var currency: String,
        var date: Date,
        var type: MoneyTransaction.Type
){
    enum class Type{
        INCOMING, EXPENSE
    }
}