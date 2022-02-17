package com.example.usecases.getphotos

import com.example.data.network.model.PhotoApi
import kotlinx.coroutines.flow.Flow

interface GetPhotosApiUseCase {
    operator fun invoke(roverName: String, sol: Int): Flow<List<PhotoApi>>
}