package com.plumcookingwine.jetpack.loadsir

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.plumcookingwine.jetpack.R

/**
 * @author kangf
 * @date  2020/12/17
 * @desc
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.base_layout_loading
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }
}