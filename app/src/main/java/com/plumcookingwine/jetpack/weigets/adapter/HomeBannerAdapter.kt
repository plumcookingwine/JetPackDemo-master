package com.plumcookingwine.jetpack.weigets.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.youth.banner.adapter.BannerAdapter


/**
 * Created by kangf on 2020/12/15.
 */
class HomeBannerAdapter(datas: MutableList<HomeBannerData>?) :
    BannerAdapter<HomeBannerData, HomeBannerAdapter.BannerViewHolder>(datas) {


    inner class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view)

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: HomeBannerData?,
        position: Int,
        size: Int
    ) {
        holder?.itemView?.let {
            Glide.with(it).load(data?.imagePath).into(it as ImageView)
        }
    }
}