package com.plumcookingwine.jetpack.data.repository

import androidx.paging.PagingData
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import kotlinx.coroutines.flow.Flow

/**
 * Created by kangf on 2020/12/14.
 */
interface Repository {

    suspend fun getBannerList(): Flow<List<HomeBannerData>?>

    fun getArticleList(): Flow<PagingData<HomeArticleData.Data>>
}