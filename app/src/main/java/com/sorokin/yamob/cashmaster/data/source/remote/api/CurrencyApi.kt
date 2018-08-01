package com.sorokin.yamob.cashmaster.data.source.remote.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("/api/v6/convert?compact=ultra")
    fun getExchangeRate(@Query("q") query: String): Call<ResponseBody>
}