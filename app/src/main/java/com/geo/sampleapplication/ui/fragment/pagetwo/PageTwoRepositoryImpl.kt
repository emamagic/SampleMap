package com.geo.sampleapplication.ui.fragment.pagetwo

import com.geo.sampleapplication.network.MyApi
import com.geo.sampleapplication.network.response.CountryResponse
import com.geo.sampleapplication.network.response.Latlng
import com.geo.sampleapplication.network.safe.ApiWrapper
import com.geo.sampleapplication.network.safe.SafeApi
import javax.inject.Inject

class PageTwoRepositoryImpl @Inject constructor(
        private val api: MyApi
) : SafeApi(), PageTwoRepository {


    override suspend fun getCountries(): ApiWrapper<List<CountryResponse>> {
       return safeApi { api.getCountries() }
    }


}