package com.example.data.repository.model

import com.example.data.network.model.PhotoApi
import com.example.data.network.model.ResponseState

data class GetPhotosResponse (
    val status: ResponseState,
    val photos: List<PhotoApi>
)