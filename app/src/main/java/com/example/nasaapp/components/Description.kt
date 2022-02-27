package com.example.nasaapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExtendedPhotoDescription(rover: String,camera: String, sol: Int) {
    Text("Photo Description", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondary, modifier = Modifier.padding(bottom = 8.dp))
    Text("Taken by $rover Mars Rover in its $camera ", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
    Text("Taken in sol number $sol", color = MaterialTheme.colors.secondary, lineHeight = 20.sp)
}