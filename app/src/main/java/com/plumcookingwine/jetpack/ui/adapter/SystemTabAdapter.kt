package com.plumcookingwine.jetpack.ui.adapter

import android.widget.TextView
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.adapter.BaseRvAdapter
import com.plumcookingwine.jetpack.base.ui.adapter.BaseRvHolder
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.databinding.ItemSystemTabBinding
import com.plumcookingwine.jetpack.utils.ResUtils

class SystemTabAdapter : BaseRvAdapter<SystemTabData>() {

    override fun layoutId(): Int {
        return R.layout.item_system_tab
    }

    override fun bindData(holder: BaseRvHolder, data: SystemTabData) {
        val binding by  holder.viewHolderBinding<ItemSystemTabBinding>()

        val flowTab = binding.flowTab

        binding.tvTitle.text = data.name

        flowTab.removeAllViews()

        data.children?.map {
            val tabView = TextView(flowTab.context)
            tabView.setTextColor(ResUtils.getResColor(R.color.colorPrimary))
            tabView.setBackgroundResource(R.drawable.border_tag_color_primary)
            tabView.text = it.name
            flowTab.addView(tabView)
        }
    }
}