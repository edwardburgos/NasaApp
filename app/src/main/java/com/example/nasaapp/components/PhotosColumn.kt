package com.example.nasaapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.domain.Photo

@Composable
fun PhotosColumn(navigate: (index: Int) -> Unit, photos: List<Photo>) {
    LazyColumn {
        items(photos.size) { index ->
            PhotoCard(navigate, photos.elementAt(index), index)
        }
    }
}