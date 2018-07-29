package com.sorokin.yamob.cashmaster.data.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import android.support.annotation.WorkerThread
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import com.sorokin.yamob.cashmaster.data.entity.Resource
import android.os.AsyncTask
import com.sorokin.yamob.cashmaster.data.entity.ApiResponse






abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()
    fun asLiveData(): LiveData<Resource<ResultType>> = result

    init {
        result.setValue(Resource.Loading(null))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource
                ) { newData -> result.setValue(Resource.Success(newData)) }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly
        result.addSource(dbSource
        ) { newData -> result.setValue(Resource.Loading(newData)) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            if (response!!.isSuccessful) {
                saveResultAndReInit(response)
            } else {
                onFetchFailed()
                result.addSource(dbSource
                ) { newData ->
                    result.setValue(
                            Resource.Failure(newData, response.throwable))
                }
            }
        }
    }

    @MainThread
    private fun saveResultAndReInit(response: ApiResponse<RequestType>) {
        /*object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response.body)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                // we specially request a new live data,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                result.addSource(loadFromDb()
                ) { newData -> result.setValue(Resource.success(newData)) }
            }
        }.execute()*/
    }


    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected fun onFetchFailed() {
    }
}