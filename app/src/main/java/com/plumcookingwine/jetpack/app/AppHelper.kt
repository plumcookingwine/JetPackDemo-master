package com.plumcookingwine.jetpack.app

import android.content.Context

/**
 * Created by kangf on 2020/12/11.
 */
object AppHelper {

    lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }


}