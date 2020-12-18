package com.plumcookingwine.jetpack

import androidx.navigation.Navigation
import com.plumcookingwine.jetpack.base.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onBackPressed() {
        val nav = Navigation.findNavController(this, R.id.fragment_main)
        if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.fragment_main) {
            nav.navigateUp()
        }  else {
            super.onBackPressed()
        }
    }
}