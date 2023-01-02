package com.nexus.rickandmortycompose.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItemModel(
    val icon: ImageVector,
    val name: String,
)

val navigationBarItems = listOf(
    NavigationBarItemModel(icon = Icons.Default.Person, name = "Characters"),
    NavigationBarItemModel(icon = Icons.Default.LocationOn, name = "Locations"),
    NavigationBarItemModel(icon = Icons.Default.Info, name = "Episodes"),
    NavigationBarItemModel(icon = Icons.Sharp.SwapHoriz, name = "Compare"),
)
