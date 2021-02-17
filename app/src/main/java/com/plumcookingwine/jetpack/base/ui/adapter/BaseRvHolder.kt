package com.plumcookingwine.jetpack.base.ui.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by kangf on 2020/12/14.
 */
open class BaseRvHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mPosition: Int = -1

    inline fun <reified T : ViewDataBinding> viewHolderBinding(): Lazy<T> =
        lazy(LazyThreadSafetyMode.NONE) {
            requireNotNull(DataBindingUtil.bind<T>(itemView)) { "cannot find the layout file" }
        }
}