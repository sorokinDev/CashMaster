package com.sorokin.yamob.cashmaster.data.entity

import java.util.*

class MoneyTransaction(
        var id: Int,
        val amount: Double,
        val currency: String,
        val date: Date,
        val targetId: Int,
        val target: MoneyTransactionTarget,
        val type: Int,
        val walletId: Int,
        val wallet: Wallet
){
    companion object {
        val INCOME = 1
        val EXPENSE = 2
        val TRANSFER = 3
    }

}