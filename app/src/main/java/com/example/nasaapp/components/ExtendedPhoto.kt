package com.example.nasaapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.Photo

@Composable
fun ExtendedPhoto(photo: Photo) {
    Column {
        Image(
            painter = rememberImagePainter(data = photo.img_src),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text("Photo taken by ${photo.camera.full_name}")
        Text("Photo taken in sol number ${photo.sol}")
        Text("Photo taken by ${photo.rover.name} Mars Rover")
    }
}