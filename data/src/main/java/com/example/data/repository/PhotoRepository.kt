package com.example.data.repository

import com.example.data.network.model.PhotoApi
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotosFromApi(roverName: String, sol: Int): Flow<List<PhotoApi>>
}