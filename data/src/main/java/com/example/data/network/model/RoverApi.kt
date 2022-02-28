package com.example.data.network.model

import android.os.Parcelable
import com.example.domain.RoverName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverApi (
    var id: Int,
    var name: RoverName,
    var landing_date: String,
    var launch_date: String,
    var status: String
): Parcelable