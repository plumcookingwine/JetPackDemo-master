package com.plumcookingwine.jetpack.ui.view.main.system

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.databinding.FragmentSystemBinding
import com.plumcookingwine.jetpack.ui.adapter.SystemPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SystemFragment : BaseFragment() {

    private val args by navArgs<SystemFragmentArgs>()

    private val mBinding: FragmentSystemBinding by binding()

    private lateinit var mArticleAdapter: SystemPageAdapter

    private val mTabs = mutableListOf<SystemTabData>()

    override fun layoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initData() {

        args.tabData?.children?.let { list ->
            mTabs.clear()
            mTabs.addAll(list)
        }

        mBinding.toolbarTitle.text = args.tabData?.name ?: "体系"

        mArticleAdapter = SystemPageAdapter(this, mTabs)
        mBinding.viewPager.adapter = mArticleAdapter
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = mTabs[position].name
        }.attach()
    }

    override fun lazyLoad() {
        lifecycleScope.launch {
            kotlin.run outside@{
                mTabs.forEachIndexed { index, systemTabData ->
                    if (systemTabData.id == args.tabCid) {
                        mBinding.viewPager.currentItem = index
                        return@outside
                    }
                }
            }
        }

    }
}