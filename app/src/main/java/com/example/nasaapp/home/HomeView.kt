package com.example.nasaapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nasaapp.components.Cameras
import com.example.nasaapp.components.FilteringModal
import com.example.nasaapp.components.PhotosColumn

@Composable
fun HomeView(viewModel: HomeViewModel) {
    val photos by viewModel.photos.observeAsState(listOf())
    val dialogState = remember { mutableStateOf(false) }
    val currentSol = remember { mutableStateOf( if (viewModel.sol.value != null) viewModel.sol.value else "1000" ) }
    val currentRover = remember { mutableStateOf( if (viewModel.roverName.value != null) viewModel.roverName.value else "Curiosity" ) }
    val sol by viewModel.sol.observeAsState("1000")
    val rover by viewModel.roverName.observeAsState("Curiosity")
    val selectedCamera by viewModel.selectedCamera.observeAsState("All")
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("Nasa App") },
                actions = {
                    IconButton(onClick = { dialogState.value = true }) {
                        Icon(Icons.Filled.Search, contentDescription = "Filter", tint = MaterialTheme.colors.secondary)
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }
    ) {
        Column {
            Cameras(availableCameras = viewModel.getAvailableCameras(rover), selectedCamera = selectedCamera, onClick = { viewModel.updateSelectedCamera(it) })
            if (photos.isNotEmpty()) PhotosColumn(
                { index -> viewModel.displayPropertyDetails(index) },
                photos = photos
            )
        }
            if (dialogState.value) {
                currentSol.value?.let { it1 ->
                    currentRover.value?.let { it2 ->
                        FilteringModal(dialogState = dialogState, solValue = it1, roverValue = it2, onValueChange = { currentSol.value = it }, getPhotos = { itRover, itSol -> viewModel.getPhotosFromFlow(itRover, itSol, selectedCamera) },
                            updateSol = { viewModel.updateSol(it) }, viewModelSol = sol, viewModelRover = rover, options =  viewModel.rovers, onOptionClick = { currentRover.value = it },
                            updateRover = { viewModel.updateRoverName(it) })
                    }
                }
            }
    }
}
