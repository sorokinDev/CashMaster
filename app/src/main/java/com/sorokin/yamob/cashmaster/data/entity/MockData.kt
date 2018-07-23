package com.sorokin.yamob.cashmaster.data.entity

import android.icu.util.Currency
import java.util.*

object MockData{
    val mainCurrency = "RUS"
    val favoriteCurrencies = mutableListOf(
            "RUB", "USD", "EUR"
    )
    val exchanges = mutableListOf(
            MoneyExchange("USD", "RUB", 63.49),
            MoneyExchange("EUR", "RUB", 73.93),
            MoneyExchange("EUR", "USD", 1.17)
    )

    val transactions = mutableListOf(
            MoneyTransaction(500.0, "RUB", Date(), MoneyTransaction.Type.INCOMING),
            MoneyTransaction(500.0, "RUB", Date(), MoneyTransaction.Type.INCOMING),
            MoneyTransaction(500.0, "RUB", Date(), MoneyTransaction.Type.INCOMING),
            MoneyTransaction(400.0, "RUB", Date(), MoneyTransaction.Type.EXPENSE)
    )

    val wallet = Wallet(1100.0, "RUB")

}