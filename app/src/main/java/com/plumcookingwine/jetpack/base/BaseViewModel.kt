package com.plumcookingwine.jetpack.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plumcookingwine.jetpack.loadsir.LoadResult

/**
 * Created by kangf on 2020/12/17.
 */
open class BaseViewModel : ViewModel() {

    // 请求状态
    val mLoadPageLiveData by lazy { MutableLiveData<LoadResult>() }

    // 请求错误
    val mErrorLiveData by lazy { MutableLiveData<String>() }

}