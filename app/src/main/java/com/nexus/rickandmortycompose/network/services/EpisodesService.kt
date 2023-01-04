package com.nexus.rickandmortycompose.network.services

import com.nexus.rickandmortycompose.models.Episode
import com.nexus.rickandmortycompose.network.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesService {

    @GET(value = "episode")
    suspend fun getAllEpisodes() : Response<ListResponseModel<Episode>>

    @GET(value = "episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int) : Response<Episode>
}