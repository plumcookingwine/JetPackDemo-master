package com.plumcookingwine.jetpack.app.initializer

import android.content.Context
import android.net.TrafficStats
import android.os.StrictMode
import androidx.startup.Initializer
import com.kingja.loadsir.core.LoadSir
import com.plumcookingwine.jetpack.app.AppHelper
import com.plumcookingwine.jetpack.loadsir.EmptyCallback
import com.plumcookingwine.jetpack.loadsir.ErrorCallback
import com.plumcookingwine.jetpack.loadsir.LoadingCallback
import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * Created by kangf on 2020/12/11.
 *
 * 项目初始化
 */
class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {

        AppHelper.init(context)

        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()

        // 开启严苛模式
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build()
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }


}