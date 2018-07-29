package com.sorokin.yamob.cashmaster.data.entity

import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import java.util.*

object MockData{
    val currencies = listOf("RUB", "USD", "EUR")
    val exchanges = listOf(
            MoneyExchange("RUB", "USD", 1 / 60.0),
            MoneyExchange("RUB", "EUR", 1 / 80.0),
            MoneyExchange("USD", "RUB", 60.0),
            MoneyExchange("EUR", "RUB", 80.0),
            MoneyExchange("USD", "EUR", 0.75),
            MoneyExchange("EUR", "USD", 1 / 0.75)
    )
    val wallets = listOf(
            Wallet(0, "Cash", 1000.0, "RUB", Wallet.CASH),
            Wallet(1, "Tinkoff",32100.0, "USD", Wallet.BANK_CARD))

    val targets = listOf(
            MoneyTransactionTarget(0, "JOB", MoneyTransaction.INCOME),
            MoneyTransactionTarget(1, "Freelance", MoneyTransaction.INCOME),
            MoneyTransactionTarget(2, "Tutoring", MoneyTransaction.INCOME),
            MoneyTransactionTarget(3, "Clothes", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(4, "Auto", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(5, "Medicine", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(6, "House", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(7, "Weekend", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(8, "Food", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(9, "Gifts", MoneyTransaction.EXPENSE),
            MoneyTransactionTarget(10, "Travelling", MoneyTransaction.EXPENSE)
    )
    val transactions = mutableListOf(
            MoneyTransaction(0, 500.0, "RUB", Date(), 0, targets[0],
                    MoneyTransaction.INCOME,0, wallets[0])
    )
}