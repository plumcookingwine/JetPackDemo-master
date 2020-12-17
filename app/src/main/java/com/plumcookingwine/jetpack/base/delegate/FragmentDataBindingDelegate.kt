package com.plumcookingwine.jetpack.base.delegate

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Fragment ViewBinding代理
 */
class FragmentDataBindingDelegate<T : ViewBinding>(
    classes: Class<T>,
    fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    init {
        fragment.lifecycle.addObserver(LifeCycleObserver())
    }

    private val bindView = classes.getMethod("bind", View::class.java)
    private var viewBinding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {

        viewBinding?.also {
            return it
        }

        @Suppress("UNCHECKED_CAST") val bind = bindView.invoke(null, thisRef.view) as T
        return bind.also { viewBinding = it }
    }

    inner class LifeCycleObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            val state = source.lifecycle.currentState
            if (state == Lifecycle.State.DESTROYED) {
                viewBinding = null
            }
        }

    }
}