package com.plumcookingwine.jetpack.ui.view.main.system

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.databinding.FragmentSystemBinding
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.ui.adapter.SystemPageAdapter

/**
 * 首页 - 体系
 */
class SystemFragment : BaseFragment() {

    private val mBinding: FragmentSystemBinding by binding()

    private val mViewModel: SystemViewModel by activityViewModels()

    private lateinit var mArticleAdapter: SystemPageAdapter

    private val mTabs = mutableListOf<SystemTabData>()

    override fun layoutId(): Int {
        return R.layout.fragment_system
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun lazyLoad() {
        super.lazyLoad()
        mViewModel.getTabSystem().observe(this) { list ->
            mBinding.viewPager.offscreenPageLimit = list?.size ?: 1
            list?.let {
                mTabs.clear()
                mTabs.addAll(it)
                mArticleAdapter.notifyDataSetChanged()
                mViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
//                // 启用闲时加载，防止Tab数量过多造成卡顿
//                Looper.myLooper()?.queue?.addIdleHandler {
//
//                    false
//                }


            }
        }
    }

    override fun enableLazyLoad(): Boolean {
        return true
    }

    override fun reload() {
        super.reload()

    }


}