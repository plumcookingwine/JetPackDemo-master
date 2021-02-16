package com.plumcookingwine.jetpack

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.connection.RealCall

/**
 * Created by kangf on 2020/12/31.
 */
class MyCallFactory(private val okHttpClient: OkHttpClient) : okhttp3.Call.Factory {

    override fun newCall(request: Request): Call {
        return MyCall()
    }
}