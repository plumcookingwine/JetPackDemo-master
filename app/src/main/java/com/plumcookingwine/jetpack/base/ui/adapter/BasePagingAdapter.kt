package com.plumcookingwine.jetpack.base.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by kangf on 2020/12/14.
 */
abstract class BasePagingAdapter<T : Any>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseRvHolder>(diffCallback) {

    companion object {
        // 定义header的key的起始位置
        private const val BASE_ITEM_TYPE_HEADER = -100000

        // 定义footer的Key的起始位置
        private const val BASE_ITEM_TYPE_FOOTER = -200000
    }

    /**
     * headerView集合
     */
    protected val mHeaderViews = SparseArrayCompat<View>()

    /**
     * footerView集合
     */
    protected val mFooterViews = SparseArrayCompat<View>()

    private var mOnItemClickListener: OnItemClickListener<T>? = null

    fun setOnItemClickListener(action: (Int, T) -> Unit) {
        this.mOnItemClickListener = object : OnItemClickListener<T> {
            override fun onClickItem(position: Int, data: T) {
                action(position, data)
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + getHeaderCount() + getFooterCount()
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position)
        }
        if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeaderCount() - itemCount)
        }
        val pos = position - getHeaderCount()
        return getViewType(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRvHolder {
        if (mHeaderViews.get(viewType) != null) {
            return BaseRvHolder(mHeaderViews.get(viewType)!!)
        }

        if (mFooterViews.get(viewType) != null) {
            return BaseRvHolder(mFooterViews.get(viewType)!!)
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId(), parent, false)
        return BaseRvHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRvHolder, position: Int) {

        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return
        }

        getItem(position - getHeaderCount())?.let { data ->
            bindData(holder, data)

            holder.itemView.setOnClickListener {
                mOnItemClickListener?.onClickItem(position, data)
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        XRvWrapperUtils.onAttachedToRecyclerView(
            recyclerView, object : XRvWrapperUtils.SpanSizeCallback {

                override fun getSpanSize(
                    layoutManager: GridLayoutManager?,
                    oldLookup: GridLayoutManager.SpanSizeLookup?,
                    position: Int
                ): Int {
                    val viewType = getItemViewType(position)
                    if (mHeaderViews[viewType] != null) {
                        return layoutManager?.spanCount ?: 1
                    } else if (mFooterViews.get(viewType) != null) {
                        return layoutManager?.spanCount ?: 1
                    }
                    return oldLookup?.getSpanSize(position) ?: 1
                }
            })
    }

    /**
     * 当Item进入这个页面的时候调用
     */
    override fun onViewAttachedToWindow(holder: BaseRvHolder) {
        val position = holder.layoutPosition
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            XRvWrapperUtils.setFullSpan(holder)
        }
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, getHeaderCount()))
    }

    open fun getViewType(position: Int): Int = 0

    abstract fun layoutId(): Int

    abstract fun bindData(holder: BaseRvHolder, data: T)

    /**
     * 获取头布局数量
     */
    fun getHeaderCount(): Int {
        return mHeaderViews.size()
    }

    /**
     * 获取脚布局数量
     */
    fun getFooterCount(): Int {
        return mFooterViews.size()
    }

    /**
     * 添加头布局
     */
    fun addHeaderView(view: View) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    /**
     * 添加脚布局
     */
    fun addFooterView(view: View) {
        mHeaderViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    /**
     * 当前位置是否是头布局
     */
    fun isHeaderViewPos(position: Int): Boolean {
        return position < getHeaderCount()
    }

    /**
     * 当前位置是否是 脚布局
     */
    fun isFooterViewPos(position: Int): Boolean {
        return position >= getHeaderCount() + itemCount
    }

    interface OnItemClickListener<T> {

        fun onClickItem(position: Int, data: T)
    }
}