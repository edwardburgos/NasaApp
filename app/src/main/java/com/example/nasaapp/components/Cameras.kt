package com.example.nasaapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.CameraName
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Cameras(availableCameras: List<CameraName>, selectedCamera: CameraName, onClick: (CameraName) -> Unit) {
    FlowRow(
        modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 8.dp)
    ) {
        repeat(availableCameras.size) {
            Camera(availableCameras.elementAt(it), selectedCamera == availableCameras.elementAt(it), onClick = onClick)
        }
    }
}