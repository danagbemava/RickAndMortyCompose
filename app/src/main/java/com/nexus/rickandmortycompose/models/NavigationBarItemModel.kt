package com.nexus.rickandmortycompose.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.SwapHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.vector.ImageVector
import com.nexus.rickandmortycompose.screens.destinations.EpisodesScreenDestination
import com.nexus.rickandmortycompose.screens.destinations.LocationsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

data class NavigationBarItemModel(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val name: String,
)

@OptIn(ExperimentalMaterial3Api::class)
val navigationBarItems = listOf(
//    NavigationBarItemModel(icon = Icons.Default.Person, name = "Characters"),
    NavigationBarItemModel(direction = LocationsScreenDestination, icon = Icons.Default.LocationOn, name = "Locations"),
    NavigationBarItemModel(direction = EpisodesScreenDestination, icon = Icons.Default.Info, name = "Episodes"),
//    NavigationBarItemModel(icon = Icons.Sharp.SwapHoriz, name = "Compare"),
)
