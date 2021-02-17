package com.plumcookingwine.jetpack.ui.view.main.system

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.databinding.FragmentSystemTabBinding
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.ui.adapter.SystemTabAdapter
import com.plumcookingwine.jetpack.ui.view.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * 首页 - 体系
 */
@AndroidEntryPoint
class SystemTabFragment : BaseFragment() {

    private val mBinding: FragmentSystemTabBinding by binding()

    private val mViewModel: SystemViewModel by viewModels()

    private lateinit var mAdapter: SystemTabAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_system_tab
    }

    override fun initListener() {
        mViewModel.mTabsLiveData.observe(this) { list ->
            mAdapter.setData(list)
            mViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
        }
    }

    override fun initData() {
        registerLoadSir(mViewModel.mLoadPageLiveData, mBinding.layRoot)
        registerRequestErrorLiveData(mViewModel.mErrorLiveData) {
            mViewModel.mLoadPageLiveData.value = LoadResult.ERROR(it)
            false
        }

        mAdapter = SystemTabAdapter()

        mBinding.rvSystem.apply {
            layoutManager = LinearLayoutManager(mActivity)
            setHasFixedSize(true)
            adapter = mAdapter
        }


        mAdapter.setOnClickTabListener { pos, cid ->
            val action = MainFragmentDirections.mainToArticleList()
                .setTabCid(cid)
                .setTabData(mAdapter.getData()?.get(pos))
            nav().navigate(action)
        }

        mAdapter.setOnItemClickListener { _, systemTabData ->
            val action = MainFragmentDirections.mainToArticleList()
                .setTabData(systemTabData)
            nav().navigate(action)
        }

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