package com.nexus.rickandmortycompose.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: Int,
    val name: String,
    @SerialName("air_date") val airDate: String,
    val episode: String,
    val characters: List<String> = emptyList(),
    val url: String
)

val dummyEpisode = Episode(
    id = 1,
    name = "Pilot",
    airDate = "",
    episode = "S01E01",
    characters = listOf(),
    url = ""
)