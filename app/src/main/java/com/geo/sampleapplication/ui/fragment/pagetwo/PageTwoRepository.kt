package com.geo.sampleapplication.ui.fragment.pagetwo

import com.geo.sampleapplication.network.response.CountryResponse
import com.geo.sampleapplication.network.safe.ApiWrapper

interface PageTwoRepository {

    suspend fun getCountries(): ApiWrapper<List<CountryResponse>>
}