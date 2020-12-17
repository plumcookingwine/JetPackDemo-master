package com.plumcookingwine.jetpack

import com.plumcookingwine.jetpack.base.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_main
}