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
            val page = params.key ?: 0
            val stickMutableList = mutableListOf<HomeArticleData.Data>()
            if (page == 0) {
                val stickResponse = service.getStickArticle()
                stickResponse.data?.let {
                    it.forEach { data -> data.isTicked = true }
                    stickMutableList.addAll(it)
                }
            } else {
                stickMutableList.clear()
            }
            val response = service.getArticleList(page)
            response.data?.datas?.let { stickMutableList.addAll(it) }
            LoadResult.Page(
                data = stickMutableList,
                prevKey = null,
                nextKey = (response.data?.curPage ?: 0),
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}