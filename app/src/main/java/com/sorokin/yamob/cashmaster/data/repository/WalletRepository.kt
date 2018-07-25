package com.sorokin.yamob.cashmaster.data.repository

import com.sorokin.yamob.cashmaster.data.entity.MockData
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import com.sorokin.yamob.cashmaster.data.entity.Wallet
import javax.inject.Inject

class WalletRepository @Inject constructor(){

    //region EXCHANGE
    fun exchangeMoney(amount: Double, sourceCurrency: String, destinationCurrency: String): Double {
        val exc = MockData.exchanges.firstOrNull {
            (it.sourceCurrency == sourceCurrency && destinationCurrency == destinationCurrency) ||
                    (it.destinationCurrency == sourceCurrency && it.sourceCurrency == destinationCurrency)
        }

        return  if (exc != null)
                    amount * if (exc.sourceCurrency == sourceCurrency) exc.rate else 1 / exc.rate
                else
                    amount
    }
    //endregion

    //region TRANSACTIONS
    fun getAllTransactions(): List<MoneyTransaction> = MockData.transactions
    fun getLastNTransactions(n: Int): List<MoneyTransaction> = MockData.transactions.takeLast(n).toList()
    fun getTransactionById(id: Int): MoneyTransaction? =
            if(id < MockData.transactions.count()) MockData.transactions[id] else null
    fun getTransactionsByType(type: MoneyTransaction.Type): List<MoneyTransaction> =
            MockData.transactions.filter { it.type == type }.toList()

    fun addTransaction(transaction: MoneyTransaction){
        val transEx = MoneyTransaction(
                exchangeMoney(transaction.amount, transaction.currency, MockData.mainCurrency),
                MockData.mainCurrency,
                transaction.date,
                transaction.type
        )
        MockData.wallet.money +=
                if (transEx.type == MoneyTransaction.Type.INCOMING) transEx.amount
                else (-transEx.amount)

        MockData.transactions.add(transEx)
    }

    fun sumAllTransactions():Double =
            getAllTransactions().sumByDouble {
                if (it.type == MoneyTransaction.Type.INCOMING) it.amount
                else -it.amount
            }


    fun sumTransactionsByType(transactionType: MoneyTransaction.Type): Double =
            getTransactionsByType(transactionType).sumByDouble { it.amount }
    //endregion

    //region WALLET
    fun getWallet() = MockData.wallet
    fun getWalletInCurrency(currency: String) =
            Wallet(
                exchangeMoney(MockData.wallet.money, MockData.wallet.currency, currency),
                currency
            )
    //endregion
}