package com.nexus.rickandmortycompose.screens.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexus.rickandmortycompose.models.Episode
import com.nexus.rickandmortycompose.models.dummyEpisode

@Composable
fun EpisodeComposable(
     episode: Episode,
     onClick: () -> Unit = {},
     modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(8.dp).clickable {
        onClick()
    }, horizontalAlignment = Alignment.Start) {
        Text(text = episode.name)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = episode.episode)
    }   
}

@Composable
@Preview
fun EpisodeComposablePreview() {
    EpisodeComposable(episode = dummyEpisode)
}