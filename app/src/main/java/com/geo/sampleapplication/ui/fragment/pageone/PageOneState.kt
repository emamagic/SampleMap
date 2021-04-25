package com.geo.sampleapplication.ui.fragment.pageone

sealed class PageOneState {
    data class NavigateToPageTwoFragment(val lat: Double ,val lon: Double): PageOneState()
    data class InvalidInput(@InputType val message: String): PageOneState()
    object CheckGPSStatus: PageOneState()
    object GpsIsDisable: PageOneState()
}