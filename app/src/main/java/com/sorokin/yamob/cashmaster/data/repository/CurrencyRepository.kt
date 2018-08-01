package com.sorokin.yamob.cashmaster.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sorokin.yamob.cashmaster.data.entity.MockData
import com.sorokin.yamob.cashmaster.data.entity.Resource
import com.sorokin.yamob.cashmaster.data.source.remote.api.CurrencyApi
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class CurrencyRepository @Inject constructor(val currencyApi: CurrencyApi) {
    fun getExchangeRate(fromCur: String, toCur: String): LiveData<Resource<Double>> {
        fun onFailure(ldRes: MutableLiveData<Resource<Double>>, t: Throwable?){
            Timber.e(t)
            val local = MockData.exchanges.firstOrNull{ it.sourceCurrency == fromCur && it.destinationCurrency == toCur }
            if(local != null){
                ldRes.postValue(Resource.Failure(local.rate, t))
            }else{
                ldRes.postValue(Resource.Failure(1.0, t))
            }
        }

        val local = MockData.exchanges.firstOrNull{ it.sourceCurrency == fromCur && it.destinationCurrency == toCur }

        val ldRes = mutableLiveDataWithVal<Resource<Double>>(Resource.Loading<Double>(local?.rate ?: 1.0))

        currencyApi.getExchangeRate("${fromCur}_${toCur}").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Timber.i("onFail")
                onFailure(ldRes, t)
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Timber.i("onResp befor succ")
                if(response != null && response.isSuccessful){
                    val strRes = response.body()?.string()
                    Timber.i("Response from API: %s", strRes)

                    if(strRes != null){
                        val sub = strRes.split(':').last().replace("}", "")
                        ldRes.postValue(Resource.Success<Double>(sub.toDoubleOrNull() ?: 1.0))
                        MockData.exchanges.firstOrNull { it.sourceCurrency == fromCur && it.destinationCurrency == toCur }?.apply { rate = sub.toDoubleOrNull() ?: rate }
                    }
                }else{
                    onFailure(ldRes, null)
                }
            }
        })

        return ldRes
    }
}