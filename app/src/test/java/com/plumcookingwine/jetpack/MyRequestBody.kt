package com.plumcookingwine.jetpack

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink

/**
 * Created by kangf on 2020/12/25.
 */
class MyRequestBody : RequestBody() {
    override fun contentType(): MediaType? {
        return null
    }

    override fun writeTo(sink: BufferedSink) {
    }

    override fun isOneShot(): Boolean {
        return true
    }
}