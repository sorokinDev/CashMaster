package com.sorokin.yamob.cashmaster.data.interactor

import android.arch.lifecycle.LiveData
import com.sorokin.yamob.cashmaster.data.entity.Resource
import com.sorokin.yamob.cashmaster.data.repository.CurrencyRepository
import com.sorokin.yamob.cashmaster.util.mutableLiveDataWithVal
import javax.inject.Inject

class WalletInteractor @Inject constructor(val currencyRepository: CurrencyRepository){

    fun getExchangeRate(fromCur: String, toCur: String): LiveData<Resource<Double>>{
        val ldRes = mutableLiveDataWithVal<Resource<Double>>(Resource.Loading<Double>(null))
        if(fromCur == toCur){
            ldRes.postValue(Resource.Success(1.0))
            return ldRes
        }
        return currencyRepository.getExchangeRate(fromCur, toCur)

    }
}