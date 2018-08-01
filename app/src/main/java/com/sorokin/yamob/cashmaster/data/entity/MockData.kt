package com.sorokin.yamob.cashmaster.data.entity

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import java.util.*

object MockData{
    val currencies = listOf("RUB", "USD")
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
            Wallet(1, "Card",32100.0, "RUB", Wallet.BANK_CARD, R.drawable.card),
            Wallet(2, "Bank", 456321.0, "RUB", Wallet.BANK_ACCOUNT, R.drawable.bank)
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
                    MoneyTransaction.INCOME,0, wallets[0]),
            MoneyTransaction(1, 700.0, "RUB", Date(), 7, targets[7],
                    MoneyTransaction.EXPENSE,0, wallets[0]),
            MoneyTransaction(2, 600.0, "RUB", Date(), 1, targets[1],
                    MoneyTransaction.INCOME,0, wallets[0]),
            MoneyTransaction(3, 1000.0, "RUB", Date(), 0, targets[0],
                    MoneyTransaction.INCOME,0, wallets[0]),
            MoneyTransaction(4, 100.0, "RUB", Date(), 4, targets[4],
                    MoneyTransaction.EXPENSE,0, wallets[0]),
            MoneyTransaction(5, 3000.0, "RUB", Date(), 3, targets[3],
                    MoneyTransaction.EXPENSE,0, wallets[0]),
            MoneyTransaction(6, 400.0, "RUB", Date(), 5, targets[5],
                    MoneyTransaction.EXPENSE,0, wallets[0]),
            MoneyTransaction(6, 500.0, "RUB", Date(), 6, targets[6],
                    MoneyTransaction.EXPENSE,0, wallets[0])
    )
    val currencySigns = mapOf(Pair("RUB", "\u20BD"), Pair("USD", "$"), Pair("EUR", "€"))

}