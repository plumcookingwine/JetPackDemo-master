package com.plumcookingwine.jetpack.weigets

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.FragmentActivity
import com.plumcookingwine.jetpack.WebActivity
import com.plumcookingwine.jetpack.base.ui.view.BaseCustomView
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.databinding.LayoutHomeBannerBinding
import com.plumcookingwine.jetpack.weigets.adapter.HomeBannerAdapter
import com.youth.banner.indicator.CircleIndicator

/**
 * Created by kangf on 2020/12/15.
 */
class HomeBannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseCustomView<List<HomeBannerData>>(context, attrs, defStyleAttr) {

    private val mBinding: LayoutHomeBannerBinding by binding()

    private val mBannerList = mutableListOf<HomeBannerData>()

    private var mAdapter: HomeBannerAdapter? = null

    override fun base() {
        mAdapter = HomeBannerAdapter(mBannerList)
        mBinding.banner
            .addBannerLifecycleObserver(context as FragmentActivity)
            .setAdapter(mAdapter)
            .setLoopTime(5000)
            .indicator = CircleIndicator(context)

        mAdapter?.setOnBannerListener { _, position ->
            val data = mBannerList[position]

            WebActivity.toWebView(
                context as FragmentActivity,
                data.url, data.title
            )
        }
    }

    override fun initData(t: List<HomeBannerData>) {
        mBannerList.clear()
        mBannerList.addAll(t)
        mBinding.banner.currentItem = 0
        mAdapter?.notifyDataSetChanged()
    }

}