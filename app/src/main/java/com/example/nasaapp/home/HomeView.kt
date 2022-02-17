package com.example.nasaapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.nasaapp.components.Camera
import com.example.nasaapp.components.PhotosColumn
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun HomeView(viewModel: HomeViewModel) {
    val photos by viewModel.photos.observeAsState(listOf())
    Surface(color = MaterialTheme.colors.background) {
        Column {
            FlowRow {
                Camera("Camara de Edward", true)
            }
            if (photos.isNotEmpty()) PhotosColumn({ index -> viewModel.displayPropertyDetails(index) }, photos = photos)
        }
    }
}