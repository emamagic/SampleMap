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

    private val _pageOneState = SingleLiveEvent<PageOneState>()
    val pageOneState: LiveData<PageOneState>
        get() = _pageOneState


    fun onSubmitClicked(lat: String? = "", lon: String? = "") = viewModelScope.launch {
        if (!lat.isNullOrEmpty() && !lon.isNullOrEmpty()){
            mLat = lat.toDouble()
            mLon = lon.toDouble()
            if (mLat!! > -85 && mLat!! < 85 && mLon!! > -85 && mLon!! < 85) _pageOneState.value = PageOneState.CheckGPSStatus
            else _pageOneState.value = PageOneState.InvalidInput(InputType.INPUT_INVALID)
        }else _pageOneState.value = PageOneState.InvalidInput(InputType.INPUT_EMPTY)

    }

    fun gpsIsEnable() = viewModelScope.launch {
        _pageOneState.value = PageOneState.NavigateToPageTwoFragment(mLat!! ,mLon!!)
    }

    fun gpsIsDisable() = viewModelScope.launch {
        _pageOneState.value = PageOneState.GpsIsDisable
    }



}