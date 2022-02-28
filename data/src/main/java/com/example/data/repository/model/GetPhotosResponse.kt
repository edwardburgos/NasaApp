package com.example.data.repository.model

import com.example.data.network.model.ResponseState
import com.example.domain.Photo

data class GetPhotosResponse (
    val status: ResponseState,
    val photos: List<Photo>
)