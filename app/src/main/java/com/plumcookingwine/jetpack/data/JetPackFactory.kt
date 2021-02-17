package com.plumcookingwine.jetpack.data

import androidx.paging.PagingConfig
import com.plumcookingwine.jetpack.data.repository.JetPackRepository
import com.plumcookingwine.jetpack.data.repository.Repository
import com.plumcookingwine.jetpack.network.AndroidWanService

object JetPackFactory {

    fun makePokemonRepository(service: AndroidWanService): Repository =
        JetPackRepository(service, pagingConfig)

    private val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 20,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 4,


        // 初始化加载数量，默认为 pageSize * 3
        initialLoadSize = 20
    )

}