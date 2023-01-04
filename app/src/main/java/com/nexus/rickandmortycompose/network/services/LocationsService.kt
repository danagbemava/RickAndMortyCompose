package com.nexus.rickandmortycompose.network.services

import com.nexus.rickandmortycompose.models.Location
import com.nexus.rickandmortycompose.network.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationsService {

    @GET(value = "location")
    suspend fun getAllLocations() : Response<ListResponseModel<Location>>

    @GET(value = "location/{id}")
    suspend fun getLocationById(@Path("id") id: Int) : Response<Location>
}