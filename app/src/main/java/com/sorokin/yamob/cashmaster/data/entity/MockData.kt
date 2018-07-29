package com.sorokin.yamob.cashmaster.data.entity

import com.sorokin.yamob.cashmaster.R
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
            Wallet(0, "Cash", 1000.0, "RUB", Wallet.CASH, R.drawable.wallet),
            Wallet(1, "Tinkoff",32100.0, "USD", Wallet.BANK_CARD, R.drawable.card),
            Wallet(2, "Bank", 456321.0, "EUR", Wallet.BANK_ACCOUNT, R.drawable.bank)
    )

    val targets = listOf(
            MoneyTransactionTarget(0, "Job", MoneyTransaction.INCOME, R.drawable.job),
            MoneyTransactionTarget(1, "Freelance", MoneyTransaction.INCOME, R.drawable.coins),
            MoneyTransactionTarget(2, "Clothes", MoneyTransaction.EXPENSE, R.drawable.clothes),
            MoneyTransactionTarget(3, "Car", MoneyTransaction.EXPENSE, R.drawable.car),
            MoneyTransactionTarget(4, "Medicine", MoneyTransaction.EXPENSE, R.drawable.medicine),
            MoneyTransactionTarget(5, "House", MoneyTransaction.EXPENSE, R.drawable.home),
            MoneyTransactionTarget(6, "Travelling", MoneyTransaction.EXPENSE, R.drawable.travel),
            MoneyTransactionTarget(7, "Food", MoneyTransaction.EXPENSE, R.drawable.product),
            MoneyTransactionTarget(8, "Phone", MoneyTransaction.EXPENSE, R.drawable.phone)

    )
    val transactions = mutableListOf(
            MoneyTransaction(0, 500.0, "RUB", Date(), 0, targets[0],
                    MoneyTransaction.INCOME,0, wallets[0])
    )
}