package com.example.data.repository.model

import com.example.data.network.model.PhotoApi

data class GetPhotosResponse (
    val status: String,
    val photos: List<PhotoApi>
)