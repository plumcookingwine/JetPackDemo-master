package com.plumcookingwine.jetpack.base.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Created by zhy on 16/6/28.
 */
object XRvWrapperUtils {

    fun onAttachedToRecyclerView(recyclerView: RecyclerView, callback: SpanSizeCallback) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val spanSizeLookup = layoutManager.spanSizeLookup
            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return callback.getSpanSize(layoutManager, spanSizeLookup, position)
                }
            }
            layoutManager.spanCount = layoutManager.spanCount
        }
    }

    fun setFullSpan(holder: RecyclerView.ViewHolder) {
        val lp = holder.itemView.layoutParams
        if (lp is StaggeredGridLayoutManager.LayoutParams) {
            lp.isFullSpan = true
        }
    }

    //设置一个接口 用来 判断是不是GridLayout
    interface SpanSizeCallback {
        fun getSpanSize(
            layoutManager: GridLayoutManager?,
            oldLookup: SpanSizeLookup?,
            position: Int
        ): Int
    }
}