package com.sorokin.yamob.cashmaster.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.content.Context
import timber.log.Timber
import android.util.DisplayMetrics
import android.util.TypedValue





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

fun Int.dpToPx(context: Context): Int {
    val resources = context.getResources()
    val metrics = resources.getDisplayMetrics()
    return (this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Int.spToPx(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), context.resources.displayMetrics).toInt()
}

fun Int.pxToSp(context: Context): Int {
    return (this / context.resources.getDisplayMetrics().scaledDensity).toInt()
}