package com.sorokin.yamob.cashmaster.data.entity

import android.icu.util.Currency

class MoneyExchange(val sourceCurrency: String, val destinationCurrency: String, var rate: Double)