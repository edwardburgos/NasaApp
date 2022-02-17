package com.example.data.repository

import android.util.Log
import com.example.data.network.ApiService
import com.example.data.network.model.PhotoApi
import com.example.data.network.model.ResponseApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PhotoRepository {

    private val refreshIntervalMsShort: Long = 1000
    private val refreshIntervalMsLong: Long = 600000

    override fun getPhotosFromApi(roverName: String, sol: String, selectedCamera: String): Flow<List<PhotoApi>> {
        var camera = if (selectedCamera == "All") null else selectedCamera.lowercase()
        return flow {
            val source = when (roverName.lowercase()) {
                "curiosity" -> apiService.getPhotosCuriosity(sol, camera)
                "opportunity" -> apiService.getPhotosOpportunity(sol, camera)
                else -> apiService.getPhotosSpirit(sol, camera)
            }
            var finalResponse = listOf<PhotoApi>()
            source.enqueue(object : Callback<ResponseApi> {
                override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            finalResponse = it.photos
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                    Log.e("Failure", "Message " + t.message)
                }
            })
            while (true) {
                emit(finalResponse)
                if (finalResponse.isEmpty()) {
                    delay(refreshIntervalMsShort)
                } else {
                    delay(refreshIntervalMsLong)
                }
            }
        }
    }
}