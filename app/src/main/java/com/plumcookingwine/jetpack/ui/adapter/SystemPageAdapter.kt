package com.plumcookingwine.jetpack.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.ui.view.main.system.SystemArticleFragment

/**
 * Created by kangf on 2020/12/21.
 *
 * 体系 adapter
 */
class SystemPageAdapter(fragment: Fragment, private val tabs: List<SystemTabData>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return SystemArticleFragment.newInstance(tabs[position].id)
    }
}