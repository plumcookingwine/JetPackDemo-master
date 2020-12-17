package com.plumcookingwine.jetpack.di.network

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by kangf on 2020/12/15.
 */
object InterceptorFactory {

    fun getLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                HttpLoggingInterceptor.Logger.DEFAULT.log(message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}