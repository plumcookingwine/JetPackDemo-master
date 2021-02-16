package com.plumcookingwine.jetpack.ui.view.main.system

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.plumcookingwine.jetpack.base.BaseViewModel
import com.plumcookingwine.jetpack.data.entity.HomeArticleData
import com.plumcookingwine.jetpack.data.entity.SystemTabData
import com.plumcookingwine.jetpack.data.repository.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by kangf on 2020/12/21.
 */
class SystemViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val mTabsLiveData by lazy { MutableLiveData<List<SystemTabData>?>() }

    val mArticleLiveData by lazy { MutableLiveData<PagingData<HomeArticleData.Data>>() }

    fun getTabSystem() {
        viewModelScope.launch {
            repository.getSystemTab().catch {
                mErrorLiveData.value = it.message
            }.collectLatest {
                mTabsLiveData.value = it
            }
        }
    }

    /**
     * 获取体系文章列表
     */
    fun getArticleList(cid: Int) {
        viewModelScope.launch {
            repository.getArticleList(cid).cachedIn(viewModelScope).catch {
                mErrorLiveData.value = it.message
            }.collectLatest {
                mArticleLiveData.value = it
            }
        }
    }
}