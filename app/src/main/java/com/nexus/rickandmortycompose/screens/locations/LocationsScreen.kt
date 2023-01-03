package com.nexus.rickandmortycompose.screens.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.nexus.rickandmortycompose.models.dummyLocation
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalMaterial3Api
@Destination
@Composable
fun LocationsScreen() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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

        LazyColumn(contentPadding = it) {
            items(count = 10) {
                LocationComposable(location = dummyLocation)
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