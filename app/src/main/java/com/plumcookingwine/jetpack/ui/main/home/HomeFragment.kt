package com.plumcookingwine.jetpack.ui.main.home

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.WebActivity
import com.plumcookingwine.jetpack.base.ui.adapter.footer.FooterAdapter
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.databinding.FragmentHomeBinding
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.recyclerview.CommonLinearDivider
import com.plumcookingwine.jetpack.ui.main.home.adapter.HomeArticleAdapter
import com.plumcookingwine.jetpack.weigets.HomeBannerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment() {

    private val mHomeViewModel: HomeViewModel by activityViewModels()

    private val mBinding: FragmentHomeBinding by binding()

    private lateinit var mHomeBannerView: HomeBannerView

    private lateinit var mAdapter: HomeArticleAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initListener() {
        super.initListener()

        registerLoadSir(mHomeViewModel.mLoadPageLiveData, mBinding.layRefresh)

        mHomeViewModel.mBannerList.observe(this@HomeFragment) {
            it?.let { mHomeBannerView.setData(it) }
        }

        mBinding.ivSearch.setOnClickListener {

        }

        mBinding.layRefresh.setOnRefreshListener {
            mHomeViewModel.getBannerList()
            mAdapter.refresh()
        }
    }

    override fun initData() {
        mAdapter = HomeArticleAdapter().apply {
            mHomeBannerView = HomeBannerView(mActivity)
            addHeaderView(mHomeBannerView)
            setOnItemClickListener { _, data ->
                WebActivity.toWebView(mActivity, data.link, data.title)
            }
        }

        mBinding.rvArticle.let {
            it.layoutManager = LinearLayoutManager(mActivity)
            it.setHasFixedSize(true)
            it.addItemDecoration(CommonLinearDivider(mActivity))
            it.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter))
        }
    }

    override fun lazyLoad() {
        mHomeViewModel.getBannerList()

        viewLifecycleOwner.lifecycleScope.launch {
            mHomeViewModel.getArticleList().observe(this@HomeFragment) {
                mHomeViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
                mBinding.layRefresh.isRefreshing = false

                it?.let {
                    mAdapter.submitData(lifecycle, it)
                }
            }

            mAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        if (mAdapter.itemCount <= 0) {
                            mHomeViewModel.mLoadPageLiveData.value = LoadResult.LOADING
                        }
                    }
                    is LoadState.Error -> {
                        val error = (it.refresh as LoadState.Error).error.message
                        mHomeViewModel.mLoadPageLiveData.value = LoadResult.ERROR(error)
                    }
                    else -> {
                        mHomeViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
                    }
                }
            }
        }
    }

    override fun reload() {
        super.reload()
        mHomeViewModel.getBannerList()
        mAdapter.refresh()
    }
}