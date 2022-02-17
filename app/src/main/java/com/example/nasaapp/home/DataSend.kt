package com.example.nasaapp.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSend (
    var roverName: String,
    var photoIndex: Int,
    var sol: String,
    var selectedCamera: String
): Parcelable