package com.plumcookingwine.jetpack.ui.view.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.plumcookingwine.jetpack.base.BaseViewModel
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.data.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by kangf on 2020/12/14.
 */
@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val mBannerLiveData by lazy { MutableLiveData<List<HomeBannerData>?>() }

    val mArticleList by lazy { MutableLiveData<PagingData<HomeArticleData.Data>>() }

    fun getBannerList() {
        viewModelScope.launch {
            repository.getBannerList().catch {
                mErrorLiveData.value = it.message
            }.collectLatest {
                mBannerLiveData.value = it
            }
        }

    }


    fun getArticleList() {
        viewModelScope.launch {
            repository.getArticleList().cachedIn(viewModelScope).collectLatest {
                mArticleList.value = it
            }

        }

    }

}