package com.example.nasaapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.data.network.model.ResponseState
import com.example.nasaapp.R
import com.example.nasaapp.components.Cameras
import com.example.nasaapp.components.FilteringModal
import com.example.nasaapp.components.PhotosColumn
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeView(viewModel: HomeViewModel, imageLoader: ImageLoader) {
    val photos by viewModel.photos.observeAsState(listOf())
    val responseState by viewModel.responseState.observeAsState(ResponseState.EMPTY)
    val dialogState = remember { mutableStateOf(false) }
    val currentSol = remember { mutableStateOf( if (viewModel.sol.value != null) viewModel.sol.value else "1000" ) }
    val currentRover = remember { mutableStateOf( if (viewModel.roverName.value != null) viewModel.roverName.value else "Curiosity" ) }
    val sol by viewModel.sol.observeAsState("1000")
    val rover by viewModel.roverName.observeAsState("Curiosity")
    val selectedCamera by viewModel.selectedCamera.observeAsState("All")
    val isRefreshing by remember { mutableStateOf(false) }

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
            if (photos.isNotEmpty()) {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = { viewModel.getPhotosFromFlow(rover, sol, selectedCamera) },
                ) {
                    PhotosColumn(
                        { index -> viewModel.displayPropertyDetails(index) },
                        photos = photos
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (responseState) {
                        ResponseState.ERROR ->  {
                            Icon(Icons.Filled.Warning, contentDescription = "Error Ocurred", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(50.dp).padding(bottom = 16.dp))
                            Text(text = "An error ocurred", style = MaterialTheme.typography.body1.plus(
                                TextStyle (color = MaterialTheme.colors.secondary)
                            ))
                        }
                        ResponseState.INITIAL -> Image(
                                painter = rememberImagePainter(R.drawable.loader, imageLoader = imageLoader),
                                contentDescription = null,
                                modifier = Modifier.width(50.dp)
                            )
                        ResponseState.EMPTY -> {
                            Image(
                                painter = rememberImagePainter(R.drawable.nodata),
                                contentDescription = null,
                                modifier = Modifier.width(80.dp).padding(bottom = 16.dp)
                            )
                            Text(text = "No results found", style = MaterialTheme.typography.body1.plus(
                                TextStyle (color = MaterialTheme.colors.secondary)
                            ))
                        }
                        ResponseState.NO_INTERNET -> {
                            Icon(Icons.Filled.WifiOff, contentDescription = "No Internet Conecction", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(50.dp).padding(bottom = 16.dp))
                            Text(text = "No internet connection", style = MaterialTheme.typography.body1.plus(
                                TextStyle (color = MaterialTheme.colors.secondary)
                            ))
                        }
                    }
                }
            }
        }

        if (dialogState.value) {
            currentSol.value?.let { currentSolValue ->
                currentRover.value?.let { currentRoverValue ->
                    FilteringModal(dialogStateUpdate = { dialogState.value = it }, solValue = currentSolValue, roverValue = currentRoverValue, onValueChange = { currentSol.value = it }, getPhotos = { itRover, itSol -> viewModel.getPhotosFromFlow(itRover, itSol, selectedCamera) },
                            updateSol = { viewModel.updateSol(it) }, viewModelSol = sol, viewModelRover = rover, options =  viewModel.rovers, onOptionClick = { currentRover.value = it },
                            updateRover = { viewModel.updateRoverName(it) })
                }
            }
        }
    }
}
