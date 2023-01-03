package com.nexus.rickandmortycompose.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
 val id: String? = null,
 val name: String? = null,
 val type: String? = null,
 val dimension: String? = null,
 val residents: List<String> = emptyList(),
 val url: String? = null
)

val dummyLocation = Location(
 name = "Location",
 url = "https://"
)