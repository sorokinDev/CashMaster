package com.sorokin.yamob.cashmaster.data.entity

import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import java.util.*

object MockData{
    val mainCurrency = "RUS"
    val favoriteCurrencies = mutableListOf(
            "RUB", "USD"
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

    val wallets = mutableLiveDataWithVal(listOf(
            Wallet(0, "Cash", 1000.0, "RUB", Wallet.Type.CASH),
            Wallet(1, "Tinkoff",32100.0, "USD", Wallet.Type.BANK_CARD),
            Wallet(2, "Sberbank", 54200.0, "RUB", Wallet.Type.BANK_CARD),
            Wallet(3, "Swiss bank", 456321.0, "EUR", Wallet.Type.BANK_ACCOUNT)))

}