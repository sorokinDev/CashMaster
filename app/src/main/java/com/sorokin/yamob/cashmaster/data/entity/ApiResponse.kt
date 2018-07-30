package com.sorokin.yamob.cashmaster.data.entity

class ApiResponse<RequestType>{
    var isSuccessful: Boolean = true
    var errorMessage: String? = null
    var throwable: Throwable? = null
}