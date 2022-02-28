package com.example.nasaapp.home

import android.os.Parcelable
import com.example.domain.CameraName
import com.example.domain.RoverName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSend (
    var roverName: RoverName,
    var photoIndex: Int,
    var sol: String,
    var selectedCamera: CameraName
): Parcelable