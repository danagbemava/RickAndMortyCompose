package com.nexus.rickandmortycompose.screens.episodes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nexus.rickandmortycompose.models.dummyEpisode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import org.intellij.lang.annotations.JdkConstants

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

        when(screenState.value) {
            is EpisodeListScreenState.Loading -> {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally) {
                    Text(text = "loading")
                }
            }
            is EpisodeListScreenState.Error -> {

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