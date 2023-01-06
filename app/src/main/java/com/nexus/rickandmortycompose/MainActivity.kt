@file:OptIn(ExperimentalMaterial3Api::class)

package com.nexus.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nexus.rickandmortycompose.models.navigationBarItems
import com.nexus.rickandmortycompose.screens.NavGraphs
import com.nexus.rickandmortycompose.screens.appCurrentDestinationAsState
import com.nexus.rickandmortycompose.screens.destinations.Destination
import com.nexus.rickandmortycompose.screens.destinations.EpisodesScreenDestination
import com.nexus.rickandmortycompose.screens.episodes.EpisodeListViewModel
import com.nexus.rickandmortycompose.screens.startAppDestination
import com.nexus.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.navGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    val navController = rememberNavController()
                   HomeScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentDestination = currentDestination,
                onItemClicked = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
       DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
//        , dependenciesContainerBuilder = {
//           dependency(EpisodesScreenDestination) {hiltViewModel<EpisodeListViewModel>()}
//       })
    }
}

@Composable
fun BottomNavigationBar(
     currentDestination: Destination,
     onItemClicked : (DirectionDestinationSpec) -> Unit
) {

    NavigationBar {
        navigationBarItems.forEach { item ->
            NavigationBarItem(
                selected = item.direction == currentDestination,
                onClick = { onItemClicked(item.direction) },
                label = {
                    Text(text = item.name)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.name)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyComposeTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}