package com.nexus.rickandmortycompose.network

@kotlinx.serialization.Serializable
data class ListResponseModel<T>(
    val info: Info,
    val results: List<T> = emptyList()
)
