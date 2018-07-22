package com.sorokin.yamob.cashmaster.data.repository

import com.sorokin.yamob.cashmaster.data.entity.MockData
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransactionType
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import java.util.*
import javax.inject.Inject

class TransactionRepository @Inject constructor(
        val exchangeRepository: ExchangeRepository
) {
    fun getAllTransactions(): List<MoneyTransaction> = MockData.transactions
    fun getLastTransactions(n: Int): List<MoneyTransaction> = MockData.transactions.takeLast(n).toList()
    fun getTransactionById(id: Int): MoneyTransaction? =
            if(id < MockData.transactions.count()) MockData.transactions[id] else null

    fun addTransaction(transaction: MoneyTransaction){
        val transEx = MoneyTransaction(
                exchangeRepository.exchangeMoney(transaction.amount, transaction.currency, MockData.mainCurrency),
                MockData.mainCurrency,
                transaction.date,
                transaction.type
        )
        MockData.wallet.money +=
                if (transEx.type == MoneyTransactionType.INCOMING) transEx.amount
                else (-transEx.amount)

        MockData.transactions.add(transEx)
    }

    fun sumAllTransactions():Double =
            MockData.transactions.sumByDouble {
                if (it.type == MoneyTransactionType.INCOMING) it.amount
                else -it.amount
            }

    fun sumTransactionsByType(transactionType: MoneyTransactionType): Double =
            MockData.transactions.sumByDouble {
                if (it.type == transactionType) it.amount
                else 0.0
            }

    fun getWallet() = MockData.wallet
    fun getWalletInCurrency(currency: String) = Wallet(
            exchangeRepository.exchangeMoney(MockData.wallet.money, MockData.wallet.currency, currency),
            currency
    )
}