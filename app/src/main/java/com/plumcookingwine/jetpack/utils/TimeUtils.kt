package com.plumcookingwine.jetpack.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.DataFormatException

/**
 * Created by kangf on 2020/12/16.
 */
fun Long?.formatString(format: String? = "yyyy-MM-dd HH:mm:ss"): String {
    if (this == null) return ""
    val date = Date(this)
    return date.formatString(format)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatString(format: String? = "yyyy-MM-dd HH:mm:ss"): String {
    try {
        val f = SimpleDateFormat(format)
        return f.format(this) ?: ""
    } catch (e: DataFormatException) {
        e.printStackTrace()
    }
    return ""
}