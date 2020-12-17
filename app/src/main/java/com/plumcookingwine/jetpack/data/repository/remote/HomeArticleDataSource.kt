package com.plumcookingwine.jetpack.data.repository.remote

import androidx.paging.PagingSource
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.network.AndroidWanService

/**
 * Created by kangf on 2020/12/17.
 */
class HomeArticleDataSource(private val service: AndroidWanService) :
    PagingSource<Int, HomeArticleData.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticleData.Data> {
        return try {
            val response = service.getArticleList(params.key ?: 0)
            LoadResult.Page(
                data = response.data?.datas ?: mutableListOf(),
                prevKey = null,
                nextKey = (response.data?.curPage ?: 0),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}