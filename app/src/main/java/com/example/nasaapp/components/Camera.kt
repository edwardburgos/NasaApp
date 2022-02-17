package com.example.nasaapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Surface
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import com.example.nasaapp.R

@Composable
fun Camera(
    cameraName: String,
    isSelected: Boolean,
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = when {
            isSelected -> colorResource(R.color.teal_200)
            else -> colorResource(R.color.purple_500)
        }
    ) {
        Text(
            text = cameraName,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )
    }
}