package com.plumcookingwine.jetpack.ui.view.main.home

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
import com.plumcookingwine.jetpack.ui.adapter.ArticleListAdapter
import com.plumcookingwine.jetpack.weigets.HomeBannerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment() {

    private val mHomeViewModel: HomeViewModel by activityViewModels()

    private val mBinding: FragmentHomeBinding by binding()

    private lateinit var mHomeBannerView: HomeBannerView

    private lateinit var mAdapter: ArticleListAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun enableLazyLoad(): Boolean {
        return true
    }

    override fun initListener() {
        registerLoadSir(mHomeViewModel.mLoadPageLiveData, mBinding.layRefresh)
        registerRequestErrorLiveData(mHomeViewModel.mErrorLiveData) { false }

        mBinding.ivSearch.setOnClickListener {
            nav().navigate(R.id.main_to_search)
        }

        mBinding.layRefresh.setOnRefreshListener {
            reload()
        }

        mHomeViewModel.mArticleList.observe(this@HomeFragment) {
            it?.let {
                mAdapter.submitData(lifecycle, it)
            }
        }

        mHomeViewModel.mBannerLiveData.observe(this@HomeFragment) {
            it?.let { mHomeBannerView.setData(it) }
        }
    }

    override fun initData() {
        mAdapter = ArticleListAdapter().apply {
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

        viewLifecycleOwner.lifecycleScope.launch {

            mAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        if (mAdapter.itemCount <= 0) {
                            mHomeViewModel.mLoadPageLiveData.value = LoadResult.LOADING
                        }
                    }
                    is LoadState.Error -> {
                        mBinding.layRefresh.isRefreshing = false
                        val error = (it.refresh as LoadState.Error).error.message
                        mHomeViewModel.mLoadPageLiveData.value = LoadResult.ERROR(error)
                    }
                    is LoadState.NotLoading -> {
                        mBinding.layRefresh.isRefreshing = false
                        mHomeViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
                    }
                }
            }
        }


        reload()
    }

    override fun reload() {
        mHomeViewModel.getArticleList()
        mHomeViewModel.getBannerList()
    }
}