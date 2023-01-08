package com.nexus.rickandmortycompose.screens.episodes

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@ExperimentalMaterial3Api
@RootNavGraph(start = true)
@Destination
@Composable
fun EpisodesScreen() {

    val vm: EpisodeListViewModel = hiltViewModel()
    val screenState = vm.screenState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Episodes")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        when (screenState.value) {
            is EpisodeListScreenState.Loading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is EpisodeListScreenState.Error -> {
                val error = (screenState.value as EpisodeListScreenState.Error).error

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = error)
                    Button(onClick = {

                    }) {
                        Text(text = "Retry")
                    }
                }
            }
            is EpisodeListScreenState.Success -> {
                val data = (screenState.value as EpisodeListScreenState.Success).episodes

                LazyColumn(contentPadding = it) {
                    items(data) { episode ->
                        EpisodeComposable(episode = episode)
                    }

                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun EpisodesScreenPreview() {
    EpisodesScreen()
}