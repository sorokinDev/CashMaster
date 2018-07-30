package com.sorokin.yamob.cashmaster.util

import android.content.Context
import android.net.ConnectivityManager
import kotlin.math.roundToLong

fun Double.toMoney() = ((this * 100).roundToLong() / 100.0)
