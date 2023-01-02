package com.nexus.rickandmortycompose.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
 val id: String?,
 val name: String?,
 val type: String? ,
 val dimension: String?,
 val residents: List<String> = emptyList(),
 val url: String?
)
