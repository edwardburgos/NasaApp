package com.example.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CameraApi (
    var id: Int,
    var name: String,
    var rover_id: Int,
    var full_name: String
): Parcelable