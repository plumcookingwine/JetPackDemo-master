package com.plumcookingwine.jetpack.base.delegate

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.plumcookingwine.jetpack.base.ui.view.BaseCustomView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 自定义View ViewBinding代理
 */
class CustomViewDataBindingDelegate<T : ViewBinding>(
    classes: Class<T>,
) : ReadOnlyProperty<BaseCustomView<*>, T> {

    private val bindView = classes.getMethod("inflate", LayoutInflater::class.java)
    private var viewBinding: T? = null

    override fun getValue(thisRef: BaseCustomView<*>, property: KProperty<*>): T {

        viewBinding?.also {
            return it
        }
        @Suppress("UNCHECKED_CAST")
        val bind = bindView.invoke(null, LayoutInflater.from(thisRef.context)) as T
        thisRef.addView(bind.root)
        return bind.also { viewBinding = it }
    }
}