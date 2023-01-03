package com.nexus.rickandmortycompose.screens.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.nexus.rickandmortycompose.models.dummyEpisode
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalMaterial3Api
@Destination
@Composable
fun EpisodesScreen() {

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

        LazyColumn(contentPadding = it) {
            items(count = 10) {
                EpisodeComposable(episode = dummyEpisode)
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