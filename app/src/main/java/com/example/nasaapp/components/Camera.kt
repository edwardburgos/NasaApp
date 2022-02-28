package com.example.nasaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import com.example.domain.CameraName

@Composable
fun Camera(
    cameraName: CameraName,
    isSelected: Boolean,
    onClick: (CameraName) -> Unit
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp).clickable(onClick = { onClick(cameraName) }),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = when {
            isSelected -> MaterialTheme.colors.primary
            else -> MaterialTheme.colors.background
        },
        border = if (!isSystemInDarkTheme() || isSelected) null else BorderStroke(1.dp, MaterialTheme.colors.secondary)
    ) {
        Text(
            text = cameraName.toString(),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(16.dp, 8.dp)
        )
    }
}