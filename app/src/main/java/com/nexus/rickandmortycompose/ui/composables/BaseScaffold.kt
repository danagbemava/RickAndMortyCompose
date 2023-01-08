package com.nexus.rickandmortycompose.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nexus.rickandmortycompose.screens.appCurrentDestinationAsState
import com.nexus.rickandmortycompose.screens.destinations.Destination
import com.nexus.rickandmortycompose.screens.startAppDestination
import com.ramcosta.composedestinations.spec.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(
    startRoute: Route,
    navController: NavHostController,
    topBar: @Composable () -> Unit,
    bottomNavBar: @Composable (Destination) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val destination = navController.appCurrentDestinationAsState().value
        ?: startRoute.startAppDestination

    Scaffold(
        topBar = topBar,
        bottomBar = {
            bottomNavBar(destination)
        },
        content = content
    )
}