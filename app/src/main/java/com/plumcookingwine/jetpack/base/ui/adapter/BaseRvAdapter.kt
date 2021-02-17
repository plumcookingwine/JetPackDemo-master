package com.plumcookingwine.jetpack.base.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by kangf on 2020/12/14.
 */
abstract class BaseRvAdapter<T : Any> : RecyclerView.Adapter<BaseRvHolder>() {

    protected var mItems: MutableList<T>? = null

    private var mOnItemClickListener: OnItemClickListener<T>? = null

    fun setOnItemClickListener(action: (Int, T) -> Unit) {
        this.mOnItemClickListener = object : OnItemClickListener<T> {
            override fun onClickItem(position: Int, data: T) {
                action(position, data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId(), parent, false)
        return BaseRvHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRvHolder, position: Int) {
        mItems?.get(position)?.let { data ->
            holder.mPosition = position
            bindData(holder, data)
            holder.itemView.setOnClickListener {
                mOnItemClickListener?.onClickItem(position, data)
            }
        }


    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    abstract fun layoutId(): Int

    abstract fun bindData(holder: BaseRvHolder, data: T)

    fun setData(data: List<T>?, isRefresh: Boolean = true) {
        if (data == null) return
        if (mItems == null) mItems = mutableListOf()
        else mItems!!.clear()
        mItems!!.addAll(data)
        if (isRefresh) notifyDataSetChanged()
    }

    fun getData(): List<T>? {
        return mItems
    }

    fun addData(data: List<T>?, isRefresh: Boolean = true) {
        if (data == null) return
        if (mItems == null) {
            mItems = mutableListOf()
            mItems!!.addAll(data)
            if (isRefresh) {
                notifyDataSetChanged()
            }
        } else {
            mItems!!.addAll(data)
            if (isRefresh) {
                notifyItemInserted(mItems!!.size)
            }
        }
    }

    fun removePosition(position: Int, isRefresh: Boolean = true) {
        if (mItems == null) return
        if (mItems!!.size <= position) return
        mItems!!.removeAt(position)
        if (isRefresh) {
            notifyItemRemoved(position)
        }
    }

    fun removeData(data: T?, isRefresh: Boolean = true) {
        if (mItems.isNullOrEmpty()) return
        val position = mItems!!.indexOf(data)
        if (position == -1) return
        removePosition(position, isRefresh)
    }

    interface OnItemClickListener<T> {

        fun onClickItem(position: Int, data: T)
    }
}