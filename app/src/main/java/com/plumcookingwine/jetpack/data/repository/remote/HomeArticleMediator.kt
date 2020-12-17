package com.plumcookingwine.jetpack.data.repository.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.plumcookingwine.jetpack.data.database.AppDatabase
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.network.AndroidWanService

/**
 * Created by kangf on 2020/12/17.
 */
@ExperimentalPagingApi
class HomeArticleMediator(private val service: AndroidWanService, private val db: AppDatabase) :
    RemoteMediator<Int, HomeArticleData.Data>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, HomeArticleData.Data>
    ): MediatorResult {

        return MediatorResult.Success(true)
//        val mHomeArticleDataDao = db.articleDao()
//
//        val pageKey = when (loadType) {
//            LoadType.REFRESH -> null
//            LoadType.PREPEND -> return MediatorResult.Success(true)
//            LoadType.APPEND -> {
//
//            }
//            else -> null
//        }


    }


}