package com.plumcookingwine.jetpack.ui.main.home.adapter

import android.content.Context
import android.util.AttributeSet
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.view.BaseCustomView
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.databinding.ItemViewHomeArticleBinding

/**
 * Created by kangf on 2020/12/14.
 */
class ArticleItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseCustomView<HomeArticleData.Data>(context, attrs, defStyleAttr) {

    private val mBinding: ItemViewHomeArticleBinding by binding()

    override fun initData(t: HomeArticleData.Data) {
        mBinding.tvTitle.text = t.title
    }


}