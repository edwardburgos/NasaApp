package com.example.data.repository

import com.example.data.repository.model.GetPhotosResponse
import com.example.domain.CameraName
import com.example.domain.RoverName
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotosFromApi(roverName: RoverName, sol: String, selectedCamera: CameraName): Flow<GetPhotosResponse>
}