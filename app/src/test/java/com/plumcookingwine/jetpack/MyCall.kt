package com.plumcookingwine.jetpack

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.Timeout

/**
 * Created by kangf on 2020/12/31.
 */
class MyCall :Call {
    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun clone(): Call {
        TODO("Not yet implemented")
    }

    override fun enqueue(responseCallback: Callback) {
        TODO("Not yet implemented")
    }

    override fun execute(): Response {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}