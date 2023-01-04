package com.nexus.rickandmortycompose.network.services

import com.nexus.rickandmortycompose.models.Character
import com.nexus.rickandmortycompose.network.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

    @GET(value = "character")
    suspend fun getAllCharacters(): Response<ListResponseModel<Character>>

    @GET(value = "character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Response<Character>
}