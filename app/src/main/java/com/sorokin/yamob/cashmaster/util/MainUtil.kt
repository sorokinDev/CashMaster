package com.sorokin.yamob.cashmaster.util

import kotlin.math.roundToLong

fun Double.toMoney() = ((this * 100).roundToLong() / 100.0)