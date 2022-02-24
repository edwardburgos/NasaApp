package com.example.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//TODO: Try to avoid adding the suffix -Api to these kind of models since it can be confusing.
// Generally speaking we add "Api" to the Retrofit interface that has all the endpoints (ApiService.kt in your case),
// and we name these models just by what they are, in this case CameraResponse instead of CameraApi.
@Parcelize
data class CameraApi (
    var id: Int,
    var name: String,
    var rover_id: Int,
    var full_name: String
): Parcelable