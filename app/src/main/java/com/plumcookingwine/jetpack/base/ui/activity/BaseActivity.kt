package com.plumcookingwine.jetpack.base.ui.activity

import android.os.Bundle
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.jaeger.library.StatusBarUtil
import com.plumcookingwine.jetpack.base.delegate.ActivityDataBindingDelegate

/**
 * Created by kangf on 2020/12/14.
 */
abstract class BaseActivity : AppCompatActivity() {

    inline fun <reified T : ViewBinding> FragmentActivity.binding() =
        ActivityDataBindingDelegate(T::class.java, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        layoutId().let {
            if (it != 0) setContentView(it)
        }
        initListener()
        initData()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        statusBarColor()
        statusBarMode()
    }

    @LayoutRes
    abstract fun layoutId(): Int

    open fun initData() {}

    open fun initListener() {}

    /**
     * 配置状态栏
     */
    open fun statusBarColor() {
        // StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null)
    }

    open fun statusBarMode() {
        // StatusBarUtil.setDarkMode(this)
    }
}