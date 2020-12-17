package com.plumcookingwine.jetpack.loadsir

/**
 * Created by kangf on 2020/12/17.
 */
sealed class LoadResult {

    object SUCCESS : LoadResult()

    object LOADING : LoadResult()

    object EMPTY : LoadResult()

    data class ERROR(val msg: String?, val isShowRetry: Boolean = true) : LoadResult()
}