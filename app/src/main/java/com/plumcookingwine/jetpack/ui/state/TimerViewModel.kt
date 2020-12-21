package com.plumcookingwine.jetpack.ui.state

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TimerViewModel @ViewModelInject constructor() : ViewModel() {

    /**
     * 倒计时所需
     */
    fun timerDown(count: Int = 60, mills: Long = 1000): LiveData<Int> {
        return flow {
            (count - 1 downTo 0).forEach {
                delay(mills)
                emit(it)
            }
        }.flowOn(Dispatchers.IO).asLiveData()
    }
}