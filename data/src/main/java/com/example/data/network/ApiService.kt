package com.example.data.network

import com.example.data.network.model.ResponseApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("curiosity/photos")
    fun getPhotosCuriosity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>

    @GET("opportunity/photos")
    fun getPhotosOpportunity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>

    @GET("spirit/photos")
    fun getPhotosSpirit(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>
}
