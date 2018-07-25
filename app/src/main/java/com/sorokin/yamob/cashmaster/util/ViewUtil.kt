package com.sorokin.yamob.cashmaster.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import timber.log.Timber

fun <T> LiveData<T>.observe(owner: LifecycleOwner, notNull: (data : T) -> Unit,
                            isNull: () -> Unit = { Timber.d("Observer: Live data is null") }){
    this.observe(owner, android.arch.lifecycle.Observer {
        if(it != null){
            notNull(it)
        }else{
            isNull()
        }
    })
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, notNull: (data : T) -> Unit) {
    this.observe(owner, android.arch.lifecycle.Observer { if(it != null) notNull(it) })
}