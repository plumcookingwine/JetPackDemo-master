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

/**
 * Created by kangf on 2020/12/14.
 */
@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    fun getBannerList(): LiveData<List<HomeBannerData>?> {
        return repository.getBannerList().catch {
            mErrorLiveData.value =  it.message
        }.asLiveData()
    }


    fun getArticleList(): LiveData<PagingData<HomeArticleData.Data>> {
        return repository.getArticleList().cachedIn(viewModelScope).asLiveData()
    }

}