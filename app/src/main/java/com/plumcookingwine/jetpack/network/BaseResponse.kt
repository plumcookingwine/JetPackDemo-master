package com.plumcookingwine.jetpack.network

data class BaseResponse<T>(
    val errorCode: Int?,
    val errorMsg: String?,
    var data: T?,
)