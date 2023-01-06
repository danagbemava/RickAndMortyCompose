package com.nexus.rickandmortycompose.screens.episodes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.rickandmortycompose.models.Episode
import com.nexus.rickandmortycompose.network.services.EpisodesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(private val episodesService: EpisodesService) : ViewModel() {

    private val _screenState = MutableStateFlow<EpisodeListScreenState>(EpisodeListScreenState.Loading)
    val screenState:  StateFlow<EpisodeListScreenState>  = _screenState

    init {
        viewModelScope.launch {
            getEpisodes()
        }
    }


    private suspend fun getEpisodes() {
        if (_screenState.value != EpisodeListScreenState.Loading) {
            _screenState.value = EpisodeListScreenState.Loading
        }

        try {
            val response = episodesService.getAllEpisodes()

            if(response.isSuccessful) {
                val data = response.body()
                if(data != null) {
                    val episodes = data.results
                    _screenState.value = EpisodeListScreenState.Success(episodes = episodes)
                }
                return
            }

            val error = response.errorBody()

            if (error != null) {
                _screenState.value = EpisodeListScreenState.Error(error = error.string())
            }

        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            _screenState.value = EpisodeListScreenState.Error(e.toString())
        }
    }

    companion object {
        const val TAG = "EpisodeListViewModel"
    }
}

sealed class EpisodeListScreenState {

    object Loading : EpisodeListScreenState()

    data class Success(val episodes: List<Episode>) : EpisodeListScreenState()

    data class Error(val error: String) : EpisodeListScreenState()
}