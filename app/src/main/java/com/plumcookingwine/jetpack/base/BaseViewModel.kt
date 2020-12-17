package com.plumcookingwine.jetpack.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plumcookingwine.jetpack.loadsir.LoadResult

/**
 * Created by kangf on 2020/12/17.
 */
open class BaseViewModel : ViewModel() {

    val mLoadPageLiveData by lazy {
        MutableLiveData<LoadResult>()
    }

}