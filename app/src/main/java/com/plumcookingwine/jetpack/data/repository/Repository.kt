package com.plumcookingwine.jetpack.data.repository

import androidx.paging.PagingData
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import kotlinx.coroutines.flow.Flow

/**
 * Created by kangf on 2020/12/14.
 */
interface Repository {

    fun getBannerList(): Flow<List<HomeBannerData>?>

    /**
     * 获取首页文章列表
     */
    fun getArticleList(): Flow<PagingData<HomeArticleData.Data>>

    /**
     * 获取文章列表
     */
    fun getArticleList(cid: Int? = null): Flow<PagingData<HomeArticleData.Data>>

    /**
     * 获取体系tab
     */
    fun getSystemTab(): Flow<List<SystemTabData>?>
}