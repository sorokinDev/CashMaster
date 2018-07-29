package com.sorokin.yamob.cashmaster.data.entity

class MoneyTransactionTarget(id: Int, name: String, val transactionType: MoneyTransaction.Type) : IdNameEntity(id, name) {
}