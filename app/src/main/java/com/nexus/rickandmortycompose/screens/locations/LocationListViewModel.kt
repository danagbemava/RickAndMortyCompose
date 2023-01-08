package com.nexus.rickandmortycompose.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.rickandmortycompose.models.Location
import com.nexus.rickandmortycompose.network.services.LocationsService
import com.nexus.rickandmortycompose.screens.episodes.EpisodeListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(private val service: LocationsService) :
    ViewModel() {

    private val _screenState =
        MutableStateFlow<LocationListScreenState>(LocationListScreenState.Loading)
    val screenState: StateFlow<LocationListScreenState> = _screenState

//    init {
//        viewModelScope.launch {
//            getLocations()
//        }
//    }

    suspend fun onEvent(event: LocationListEvents) {
        when(event) {
            LocationListEvents.LoadData -> {
                getLocations()
            }
        }
    }

    private suspend fun getLocations() {
        if (_screenState.value != LocationListScreenState.Loading) {
            _screenState.value = LocationListScreenState.Loading
        }

        try {
            val response = service.getAllLocations()

            if (response.isSuccessful) {
                val data = response.body()

                if (data != null) {
                    _screenState.value = LocationListScreenState.Success(data.results)
                }
            } else {
                val error = response.errorBody().toString()

                _screenState.value = LocationListScreenState.Error(error)
            }

        } catch (e: Exception) {
            _screenState.value = LocationListScreenState.Error(e.toString())
        }
    }

    companion object {
        const val TAG = "LocationListViewModel"
    }
}

sealed class LocationListScreenState {

    object Loading : LocationListScreenState()

    data class Error(val error: String) : LocationListScreenState()

    data class Success(val locations: List<Location>) : LocationListScreenState()
}

sealed class LocationListEvents {

    object LoadData : LocationListEvents()
}