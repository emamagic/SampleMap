package com.geo.sampleapplication.ui.fragment.pagetwo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PageTwoViewModel @Inject constructor(
        private val repository: PageTwoRepository
): ViewModel() {



}