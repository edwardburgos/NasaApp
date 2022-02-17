package com.example.nasaapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.Photo

@Composable
fun PhotoCard(
    navigate: (index: Int) -> Unit,
    photo: Photo,
    index: Int
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { navigate(index) })
    ) {
        Image(
            painter = rememberImagePainter(data = photo.img_src),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}