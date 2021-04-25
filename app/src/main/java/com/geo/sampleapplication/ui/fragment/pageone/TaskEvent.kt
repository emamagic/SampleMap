package com.geo.sampleapplication.ui.fragment.pageone

sealed class TasksEvent {
    data class NavigateToPageTwoFragment(val lat: Double ,val lon: Double): TasksEvent()
    data class InvalidInput(@InputType val message: String): TasksEvent()
    object CheckGPSStatus: TasksEvent()
    object GpsIsDisable: TasksEvent()
}