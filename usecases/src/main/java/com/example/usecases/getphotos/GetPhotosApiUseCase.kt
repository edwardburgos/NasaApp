package com.example.usecases.getphotos

import com.example.data.repository.model.GetPhotosResponse
import kotlinx.coroutines.flow.Flow

interface GetPhotosApiUseCase {
    operator fun invoke(roverName: String, sol: String, selectedCamera: String): Flow<GetPhotosResponse>
}