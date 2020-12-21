package com.plumcookingwine.jetpack.network

import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by kangf on 2020/12/15.
 */
interface AndroidWanService {

    /**
     * 首页Banner图片
     */
    @GET("banner/json")
    suspend fun getBannerList(): BaseResponse<List<HomeBannerData>>

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    suspend fun getStickArticle(): BaseResponse<List<HomeArticleData.Data>>

    /**
     * 文章列表
     * @param page 页码
     * @param cid 体系id，用于获取体系数据
     *
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int? = null,
    ): BaseResponse<HomeArticleData>


    /**
     * 获取体系的tab
     */
    @GET("tree/json")
    suspend fun getSystemTab(): BaseResponse<List<SystemTabData>>
}