package com.plumcookingwine.jetpack.network

import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by kangf on 2020/12/15.
 */
interface AndroidWanService {

    @GET("banner/json")
    suspend fun getBannerList(): BaseResponse<List<HomeBannerData>>

    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): BaseResponse<HomeArticleData>
}