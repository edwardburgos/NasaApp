package com.example.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoApi (
    var id: Int,
    var sol: Int,
    var camera: CameraApi,
    var img_src: String,
    var earth_date: String,
    var rover: RoverApi
): Parcelable