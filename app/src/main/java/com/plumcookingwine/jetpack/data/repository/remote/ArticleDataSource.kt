package com.plumcookingwine.jetpack.data.repository.remote

import androidx.paging.PagingSource
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.network.AndroidWanService

/**
 * Created by kangf on 2020/12/17.
 */
class ArticleDataSource(
    private val service: AndroidWanService,
    private val cid: Int? = null
) : PagingSource<Int, HomeArticleData.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticleData.Data> {
        return try {
            val page = params.key ?: 0
            val response = service.getArticleList(page, cid)
            val datas = response.data?.datas
            LoadResult.Page(
                data = datas ?: mutableListOf(),
                prevKey = null,
                nextKey = if (datas.isNullOrEmpty()) null else response.data?.curPage ?: 0,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}