package com.example.data.repository

import com.example.data.repository.model.GetPhotosResponse
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotosFromApi(roverName: String, sol: String, selectedCamera: String): Flow<GetPhotosResponse>
}