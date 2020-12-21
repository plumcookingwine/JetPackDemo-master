package com.plumcookingwine.jetpack.ui.view.main.system

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.plumcookingwine.jetpack.base.BaseViewModel
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.data.repository.Repository
import kotlinx.coroutines.flow.catch

/**
 * Created by kangf on 2020/12/21.
 */
class SystemViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    fun getTabSystem(): LiveData<List<SystemTabData>?> {
        return repository.getSystemTab().catch {
            mErrorLiveData.value = it.message
        }.asLiveData()
    }

    /**
     * 获取体系文章列表
     */
    fun getArticleList(cid: Int): LiveData<PagingData<HomeArticleData.Data>> {
        return repository.getArticleList(cid).cachedIn(viewModelScope).asLiveData()
    }
}