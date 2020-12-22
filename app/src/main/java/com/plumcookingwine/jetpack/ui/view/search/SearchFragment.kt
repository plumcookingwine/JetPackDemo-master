package com.plumcookingwine.jetpack.ui.view.search

import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.databinding.FragmentSearchBinding

/**
 * Created by kangf on 2020/12/18.
 */
class SearchFragment : BaseFragment() {

    private val mBinding: FragmentSearchBinding by binding()

    override fun layoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initListener() {
        super.initListener()
        mBinding.toolBar.setNavigationOnClickListener {
            // 回退到上一页
            nav().popBackStack()
        }
    }

    override fun initData() {

    }
}