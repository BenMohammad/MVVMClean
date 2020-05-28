package com.mvvmclean

data class Data<RequestData> (var responseType: Status, var data: RequestData? = null, var error: Throwable? = null)

enum class Status{SUCCESSFULL, ERROR, LOADING}