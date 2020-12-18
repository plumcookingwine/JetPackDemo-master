package com.plumcookingwine.jetpack.ui.adapter

import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.adapter.BasePagingAdapter
import com.plumcookingwine.jetpack.base.ui.adapter.BaseRvHolder
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.databinding.ItemViewHomeArticleBinding

/**
 * Created by kangf on 2020/12/14.
 */
class ArticleListAdapter : BasePagingAdapter<HomeArticleData.Data>(HomeArticleData.Data.mDiffer) {

    override fun layoutId(): Int {
        return R.layout.item_view_home_article
    }

    override fun bindData(holder: BaseRvHolder, data: HomeArticleData.Data) {
        val binding by holder.viewHolderBinding<ItemViewHomeArticleBinding>()
        if (data.author.isNullOrEmpty()) {
            data.author = data.shareUser
        }
        binding.data = data
        binding.executePendingBindings()
    }
}