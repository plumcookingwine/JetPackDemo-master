package com.plumcookingwine.jetpack.base.ui.adapter.footer

import android.view.View
import androidx.paging.LoadState
import com.plumcookingwine.jetpack.base.ui.adapter.BaseRvHolder

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }