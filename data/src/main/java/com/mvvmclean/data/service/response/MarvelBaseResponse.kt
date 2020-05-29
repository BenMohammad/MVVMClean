package com.mvvmclean.data.service.response

class MarvelBaseResponse<T> (
    var code: Int,
    var status: String,
    var data: T?
)