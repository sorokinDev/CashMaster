package com.sorokin.yamob.cashmaster.util

import android.arch.lifecycle.MutableLiveData

fun <T> mutableLiveDataWithVal(defVal: T) = MutableLiveData<T>().apply { value =  defVal }