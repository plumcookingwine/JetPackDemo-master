package com.plumcookingwine.jetpack.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by kangf on 2020/12/11.
 */
@HiltAndroidApp
class JetPackApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}