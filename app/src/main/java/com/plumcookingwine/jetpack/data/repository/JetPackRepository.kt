package com.plumcookingwine.jetpack.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
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

    override suspend fun getBannerList(): Flow<List<HomeBannerData>?> {
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

}