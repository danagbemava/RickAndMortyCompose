package com.nexus.rickandmortycompose.network

@kotlinx.serialization.Serializable
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
