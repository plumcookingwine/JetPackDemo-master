package com.plumcookingwine.jetpack.base.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.plumcookingwine.jetpack.base.delegate.CustomViewDataBindingDelegate

/**
 * Created by kangf on 2020/12/14.
 */
abstract class BaseCustomView<T> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mIsLoaded: Boolean = false

    init {
        init()
    }

    /**
     * 对ViewBinding进行代理
     */
    inline fun <reified T : ViewBinding> binding() = CustomViewDataBindingDelegate(T::class.java)

    private fun init() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        // View.inflate(context, layoutId(), this)
        // base()
    }

    fun setData(t: T) {
        initData(t)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!mIsLoaded) {
            base()
            mIsLoaded = true
        }
    }

    // @LayoutRes
    // abstract fun layoutId(): Int

    abstract fun initData(t: T)

    open fun base() {

    }
}