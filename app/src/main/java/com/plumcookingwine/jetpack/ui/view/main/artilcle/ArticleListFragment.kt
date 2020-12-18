package com.plumcookingwine.jetpack.ui.view.main.artilcle

import android.os.Bundle
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.fragment.BaseFragment

/**
 * Created by kangf on 2020/12/18.
 *
 * 文章列表，此Fragment可以复用：
 *
 * 体系文章列表 mListType = 0
 * 搜索文章     mSystemId = 1
 *
 */
class ArticleListFragment : BaseFragment() {

    companion object {
        const val ARG_KEY_TYPE = "ARG_KEY_TYPE_SYSTEM"
        const val ARG_KEY_SYSTEM_ID = "ARG_KEY_SYSTEM_ID"

        const val TYPE_SYSTEM = 0
        const val TYPE_SEARCH = 1

    }


    private var mSystemId: Int = -1

    private var mListType = TYPE_SYSTEM

    override fun layoutId(): Int {
        return R.layout.fragment_article_list
    }

    override fun getBundle(bundle: Bundle) {
        mListType = bundle.getInt(ARG_KEY_TYPE)
        mSystemId = bundle.getInt(ARG_KEY_SYSTEM_ID)
    }

    override fun initListener() {
    }

    override fun initData() {

    }

    override fun reload() {

    }
}