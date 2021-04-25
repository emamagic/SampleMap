package com.geo.sampleapplication.ui.fragment.pageone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geo.sampleapplication.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageOnViewModel @Inject constructor() : ViewModel() {

    private var mLat: Double? = null
    private var mLon: Double? = null

    private val _taskEvent = SingleLiveEvent<TasksEvent>()
    val tasksEvent: LiveData<TasksEvent>
        get() = _taskEvent


    fun onSubmitClicked(lat: String? = "", lon: String? = "") = viewModelScope.launch {
        if (!lat.isNullOrEmpty() && !lon.isNullOrEmpty()){
            mLat = lat.toDouble()
            mLon = lon.toDouble()
            if (mLat!! > -85 && mLat!! < 85 && mLon!! > -85 && mLon!! < 85) _taskEvent.value = TasksEvent.CheckGPSStatus
            else _taskEvent.value = TasksEvent.InvalidInput(InputType.INPUT_INVALID)
        }else _taskEvent.value = TasksEvent.InvalidInput(InputType.INPUT_EMPTY)

    }

    fun gpsIsEnable() = viewModelScope.launch {
        _taskEvent.value = TasksEvent.NavigateToPageTwoFragment(mLat!! ,mLon!!)
    }

    fun gpsIsDisable() = viewModelScope.launch {
        _taskEvent.value = TasksEvent.GpsIsDisable
    }



}