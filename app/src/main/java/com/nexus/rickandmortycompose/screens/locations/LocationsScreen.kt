package com.nexus.rickandmortycompose.screens.locations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexus.rickandmortycompose.models.dummyLocation
import com.nexus.rickandmortycompose.screens.episodes.EpisodeListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@ExperimentalMaterial3Api
@Destination
@Composable
fun LocationsScreen(
    vm: LocationListViewModel = hiltViewModel()
) {

    val screenState = vm.screenState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            vm.onEvent(LocationListEvents.LoadData)
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Locations")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        when (screenState.value) {
            is LocationListScreenState.Error -> {
                val error = (screenState.value as LocationListScreenState.Error).error
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = error)
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(onClick = { coroutineScope.launch {
                        vm.onEvent(LocationListEvents.LoadData)
                    } }) {
                        Text(text = "Retry")
                    }
                }
            }
            LocationListScreenState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is LocationListScreenState.Success -> {
                val data = (screenState.value as LocationListScreenState.Success).locations

                LazyColumn(contentPadding = it) {
                    items(data) { location ->
                        LocationComposable(location = location)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LocationsScreenPreview() {
    LocationsScreen()
}