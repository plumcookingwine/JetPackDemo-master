package com.plumcookingwine.jetpack.loadsir

import com.kingja.loadsir.callback.Callback
import com.plumcookingwine.jetpack.R

/**
 * @author kangf
 * @date  2020/12/17
 * @desc
 */
class ErrorCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.base_layout_error
    }
}