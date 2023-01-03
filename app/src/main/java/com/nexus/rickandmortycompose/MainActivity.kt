@file:OptIn(ExperimentalMaterial3Api::class)

package com.nexus.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nexus.rickandmortycompose.models.NavigationBarItemModel
import com.nexus.rickandmortycompose.models.navigationBarItems
import com.nexus.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationBarItems.forEach {
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        label = {
                            Text(text = it.name)
                        },
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.name)
                        })
                }
            }
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyComposeTheme {
        HomeScreen()
    }
}