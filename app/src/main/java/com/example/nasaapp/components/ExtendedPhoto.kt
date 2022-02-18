package com.example.nasaapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.domain.Photo

@Composable
fun ExtendedPhoto(photo: Photo) {
    Column {
        Image(
            painter = rememberImagePainter(data = photo.img_src),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(32.dp).padding(bottom = 0.dp)
        ) {
            Text("Photo Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondary, modifier = Modifier.padding(bottom = 8.dp))
            Text("Taken by ${photo.rover.name} Mars Rover in its ${photo.camera.full_name} ", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
            Text("Taken in sol number ${photo.sol}", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
        }

    }
}