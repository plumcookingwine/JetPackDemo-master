package com.plumcookingwine.jetpack.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.plumcookingwine.jetpack.base.BaseViewModel
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.HomeBannerData
import com.plumcookingwine.jetpack.data.repository.Repository
import com.plumcookingwine.jetpack.loadsir.LoadResult
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

    val mBannerList = MutableLiveData<List<HomeBannerData>>()

    fun getBannerList() {
        viewModelScope.launch {
            repository.getBannerList()
                .catch {

                }
                .collectLatest {
                    mBannerList.value = it
                }
        }

    }


    fun getArticleList(): LiveData<PagingData<HomeArticleData.Data>> {
        return repository.getArticleList().cachedIn(viewModelScope)
            .onStart {
                mLoadPageLiveData.value = LoadResult.LOADING
            }
            .asLiveData()
    }

}