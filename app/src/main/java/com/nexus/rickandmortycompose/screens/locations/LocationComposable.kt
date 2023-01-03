package com.nexus.rickandmortycompose.screens.locations

import com.nexus.rickandmortycompose.models.Location


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexus.rickandmortycompose.models.dummyLocation

@Composable
fun LocationComposable(
    location: Location,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth().padding(8.dp).clickable {
        onClick()
    }, horizontalAlignment = Alignment.Start) {
        Text(text = location.name ?: "N/A")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = location.url ?: "N/A")
    }
}

@Composable
@Preview
fun LocationComposablePreview() {
   LocationComposable(location = dummyLocation)
}