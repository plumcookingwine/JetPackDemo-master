package com.plumcookingwine.jetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.data.repository.remote.ArticleDataSource
import com.plumcookingwine.jetpack.data.repository.remote.HomeArticleDataSource
import com.plumcookingwine.jetpack.network.AndroidWanService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by kangf on 2020/12/14.
 */
class JetPackRepository(
    private val service: AndroidWanService,
    private val pagingConfig: PagingConfig,
    // private val db: AppDatabase
) : Repository {

    override fun getBannerList(): Flow<List<HomeBannerData>?> {
        return flow {
            val data = service.getBannerList()
            emit(data)
        }.map {
            it.data
        }.flowOn(Dispatchers.IO)

    }

    override fun getArticleList(): Flow<PagingData<HomeArticleData.Data>> {
        return Pager(pagingConfig) { HomeArticleDataSource(service) }.flow
    }

    override fun getArticleList(cid: Int?): Flow<PagingData<HomeArticleData.Data>> {
        return Pager(pagingConfig) { ArticleDataSource(service, cid) }.flow
    }

    override fun getSystemTab(): Flow<List<SystemTabData>?> {
        return flow {
            val data = service.getSystemTab()
            emit(data)
        }.map {
            it.data
        }.flowOn(Dispatchers.IO)
    }


}