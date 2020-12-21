package com.plumcookingwine.jetpack.ui.view.splash

import androidx.fragment.app.activityViewModels
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.ui.state.TimerViewModel

/**
 * Created by kangf on 2020/12/21.
 */
class SplashFragment : BaseFragment() {

    private val mViewModel: TimerViewModel by activityViewModels()

    override fun layoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initData() {
        mViewModel.timerDown(3).observe(this) {
            if(it == 0) {
                nav().navigate(R.id.action_splash_to_main)
            }
        }
    }
}