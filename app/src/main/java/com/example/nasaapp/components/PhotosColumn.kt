package com.example.nasaapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.Photo

@Composable
fun PhotosColumn(navigate: (index: Int) -> Unit, photos: List<Photo>) {
    LazyColumn (modifier = Modifier.fillMaxHeight()) {
        items(photos.size) { index ->
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Column(modifier = Modifier.weight(1f)) {
                    if (index * 3 < photos.size) PhotoCard(navigate, photos.elementAt(index * 3), index * 3)
                }
                Column(modifier = Modifier.weight(1f)) {
                    if ((index * 3) + 1 < photos.size) PhotoCard(navigate, photos.elementAt((index * 3) + 1), (index * 3) + 1)
                }
                Column(modifier = Modifier.weight(1f)) {
                    if ((index * 3) + 2 < photos.size) PhotoCard(navigate, photos.elementAt((index * 3) + 2), (index * 3) + 2)
                }
            }
        }
    }
}