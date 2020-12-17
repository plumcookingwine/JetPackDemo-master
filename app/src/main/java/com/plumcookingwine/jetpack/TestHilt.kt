package com.plumcookingwine.jetpack

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

/**
 * Created by kangf on 2020/12/11.
 */
class TestHilt @Inject constructor() {

    fun test(context: Context) {
        Toast.makeText(context, "hilt测试", Toast.LENGTH_SHORT).show()
    }
}