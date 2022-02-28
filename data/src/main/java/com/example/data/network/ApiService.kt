package com.example.data.network

import com.example.data.network.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("curiosity/photos")
    suspend fun getPhotosCuriosity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Response<ResponseApi>

    @GET("opportunity/photos")
    suspend fun getPhotosOpportunity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Response<ResponseApi>

    @GET("spirit/photos")
    suspend fun getPhotosSpirit(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Response<ResponseApi>
}
