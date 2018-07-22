package com.sorokin.yamob.cashmaster.data.entity

import android.icu.util.CurrencyAmount
import java.util.*

enum class MoneyTransactionType{
    INCOMING, EXPENSE
}

class MoneyTransaction(
        var amount: Double,
        var currency: String,
        var date: Date,
        var type: MoneyTransactionType
)