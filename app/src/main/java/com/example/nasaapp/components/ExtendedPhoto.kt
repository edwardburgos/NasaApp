package com.example.nasaapp.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.domain.Photo

@Composable
fun ExtendedPhoto(photo: Photo) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(data = photo.img_src),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    // TODO: To avoid boilerplate code this could be moved to a separated component
                    //  where you only pass in the data it needs.
                    Text("Photo Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondary, modifier = Modifier.padding(bottom = 8.dp))
                    Text("Taken by ${photo.rover.name} Mars Rover in its ${photo.camera.full_name} ", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
                    Text("Taken in sol number ${photo.sol}", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
                }
            }
        }
        else -> {
            Column {
                Image(
                    painter = rememberImagePainter(data = photo.img_src),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .padding(bottom = 0.dp)
                ) {
                    Text("Photo Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondary, modifier = Modifier.padding(bottom = 8.dp))
                    Text("Taken by ${photo.rover.name} Mars Rover in its ${photo.camera.full_name} ", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
                    Text("Taken in sol number ${photo.sol}", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
                }

            }
        }
    }
}