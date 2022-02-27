package com.example.data.network

import com.example.data.network.model.ResponseApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//TODO: Retrofit has built-in support for suspend functions, which would integrate seamlessly with your code considering
// you're already using coroutines and flow. It also has a response wrapper with various utilities.
// Instead of having Call<ResponseApi> it could be Response<ResponseApi>.
/**
* @see retrofit2.Response
* */
interface ApiService {
    @GET("curiosity/photos")
    fun getPhotosCuriosity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>

    @GET("opportunity/photos")
    fun getPhotosOpportunity(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>

    @GET("spirit/photos")
    fun getPhotosSpirit(@Query("sol") sol: String, @Query("camera") selectedCamera: String?): Call<ResponseApi>
}
