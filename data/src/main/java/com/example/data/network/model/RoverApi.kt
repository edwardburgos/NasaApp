package com.example.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverApi (
    var id: Int,
    var name: String,
    var landing_date: String,
    var launch_date: String,
    var status: String
): Parcelable