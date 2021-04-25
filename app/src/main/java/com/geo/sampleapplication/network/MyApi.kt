package com.geo.sampleapplication.network

import com.geo.sampleapplication.network.response.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("rest/v2/all")
    suspend fun getCountries(): Response<List<CountryResponse>>


}