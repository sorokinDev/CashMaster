package com.sorokin.yamob.cashmaster.data.repository

import com.sorokin.yamob.cashmaster.data.entity.MockData
import java.util.*

class ExchangeRepository {
    fun exchangeMoney(amount: Double, sourceCurrency: String, destinationCurrency: String): Double {
        val exc = MockData.exchanges.firstOrNull {
            (it.sourceCurrency == sourceCurrency && destinationCurrency == destinationCurrency) ||
                (it.destinationCurrency == sourceCurrency && it.sourceCurrency == destinationCurrency)
        }

        return if (exc != null) amount * if (exc.sourceCurrency == sourceCurrency) exc.rate else 1 / exc.rate
        else amount

    }
}