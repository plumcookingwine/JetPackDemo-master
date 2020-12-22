package com.plumcookingwine.jetpack.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import com.plumcookingwine.jetpack.app.AppHelper

/**
 * Created by kangf on 2020/12/14.
 */
object ResUtils {

    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun getColorStateList(@ColorRes id: Int): ColorStateList =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            AppHelper.mContext.getColorStateList(id)
        } else {
            @Suppress("DEPRECATION")
            AppHelper.mContext.resources.getColorStateList(id)
        }

    fun getResColor(@ColorRes id: Int): Int =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            AppHelper.mContext.getColor(id)
        } else {
            @Suppress("DEPRECATION")
            AppHelper.mContext.resources.getColor(id)
        }
}



