package com.plumcookingwine.jetpack.base.ui.adapter.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.plumcookingwine.jetpack.R
import com.plumcookingwine.jetpack.base.ui.adapter.BasePagingAdapter
import com.plumcookingwine.jetpack.base.ui.adapter.BaseRvHolder
import com.plumcookingwine.jetpack.databinding.FooterItemNetworkStateBinding

/**
 * @author kangf
 * @date  2020/12/16
 * @desc
 */
class FooterAdapter(val adapter: BasePagingAdapter<*>) : LoadStateAdapter<BaseRvHolder>() {

    override fun onBindViewHolder(holder: BaseRvHolder, loadState: LoadState) {
        val params = holder.itemView.layoutParams
        if (params is StaggeredGridLayoutManager.LayoutParams) {
            params.isFullSpan = true
        }

        val mBinding by holder.viewHolderBinding<FooterItemNetworkStateBinding>()

        mBinding.apply {
            // 正在加载，显示进度条
            progress.isVisible = loadState is LoadState.Loading
            // 加载失败，显示并点击重试按钮
            retryButton.isVisible = loadState is LoadState.Error
            retryButton.setOnClickListener { adapter.retry() }
            // 加载失败显示错误原因
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BaseRvHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.footer_item_network_state, parent, false)
        return BaseRvHolder(view)
    }
}