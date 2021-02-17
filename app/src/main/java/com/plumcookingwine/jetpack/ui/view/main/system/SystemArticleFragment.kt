package com.plumcookingwine.jetpack.ui.view.main.system

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.WebActivity
import com.plumcookingwine.jetpack.base.ui.adapter.footer.FooterAdapter
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment
import com.plumcookingwine.jetpack.databinding.FragmentSystemArticleBinding
import com.plumcookingwine.jetpack.loadsir.LoadResult
import com.plumcookingwine.jetpack.recyclerview.CommonLinearDivider
import com.plumcookingwine.jetpack.ui.adapter.ArticleListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by kangf on 2020/12/21.
 *
 * 体系文章列表
 */
@AndroidEntryPoint
class SystemArticleFragment : BaseFragment() {

    private val mViewModel: SystemViewModel by viewModels()

    private val mBinding: FragmentSystemArticleBinding by binding()

    private var mCid: Int = -1

    private lateinit var mAdapter: ArticleListAdapter

    override fun getBundle(bundle: Bundle) {
        super.getBundle(bundle)
        mCid = bundle.getInt(ARG_KEY_CID, -1)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_system_article
    }

    override fun initListener() {
        super.initListener()
        mViewModel.mArticleLiveData.observe(this) {
            mBinding.layRefresh.isRefreshing = false
            mAdapter.submitData(lifecycle, it)
        }

        mBinding.layRefresh.setOnRefreshListener {
            mAdapter.refresh()
        }
    }

    override fun initData() {

        registerLoadSir(mViewModel.mLoadPageLiveData, mBinding.layRefresh)

        mAdapter = ArticleListAdapter().apply {
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
                            mViewModel.mLoadPageLiveData.value = LoadResult.LOADING
                        }
                    }
                    is LoadState.Error -> {
                        val error = (it.refresh as LoadState.Error).error.message
                        mViewModel.mLoadPageLiveData.value = LoadResult.ERROR(error)
                    }
                    else -> {
                        mViewModel.mLoadPageLiveData.value = LoadResult.SUCCESS
                    }
                }
            }
        }

        mViewModel.getArticleList(mCid)
    }

    override fun enableLazyLoad(): Boolean {
        return true
    }

    override fun reload() {
        mViewModel.getArticleList(mCid)
    }

    companion object {

        private const val ARG_KEY_CID = "ARG_KEY_CID"

        @JvmStatic
        fun newInstance(cid: Int?): SystemArticleFragment =
            SystemArticleFragment().apply {
                cid?.let {
                    arguments = Bundle().also {
                        it.putInt(ARG_KEY_CID, cid)
                    }
                }

            }
    }

}