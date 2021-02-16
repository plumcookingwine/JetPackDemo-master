package com.plumcookingwine.jetpack.ui.view.main.system

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.databinding.FragmentSystemBinding
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.ui.adapter.SystemPageAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * 首页 - 体系
 */
@AndroidEntryPoint
class SystemTabFragment : BaseFragment() {

    private val mBinding: FragmentSystemBinding by binding()

    private val mViewModel: SystemViewModel by viewModels()

    private lateinit var mArticleAdapter: SystemPageAdapter

    private val mTabs = mutableListOf<SystemTabData>()

    override fun layoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initListener() {
        mViewModel.mTabsLiveData.observe(this) { list ->
            mBinding.viewPager.offscreenPageLimit = list?.size ?: 1
            list?.let {
                mTabs.clear()
                mTabs.addAll(it)
                mArticleAdapter.notifyDataSetChanged()
                mViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
            }
        }
    }

    override fun initData() {
        registerLoadSir(mViewModel.mLoadPageLiveData, mBinding.layRoot)
        registerRequestErrorLiveData(mViewModel.mErrorLiveData) {
            mViewModel.mLoadPageLiveData.value = LoadResult.ERROR(it)
            false
        }

        mArticleAdapter = SystemPageAdapter(this, mTabs)
        mBinding.viewPager.adapter = mArticleAdapter

        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = mTabs[position].name
        }.attach()
    }

    override fun lazyLoad() {
        mViewModel.getTabSystem()
    }

    override fun enableLazyLoad(): Boolean {
        return true
    }

    override fun reload() {
        mViewModel.getTabSystem()
    }


}