package com.example.data.repository

import android.util.Log
import com.example.data.network.ApiService
import com.example.data.network.model.PhotoApiMapper
import com.example.data.network.model.ResponseState
import com.example.data.repository.model.GetPhotosResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiMapper: PhotoApiMapper
) : PhotoRepository {

    private val refreshIntervalMsShort: Long = 1000
    private val refreshIntervalMsLong: Long = 600000

    override fun getPhotosFromApi(roverName: String, sol: String, selectedCamera: String): Flow<GetPhotosResponse> {
        val camera = if (selectedCamera == "All") null else selectedCamera.lowercase()
        return flow {
            var finalResponse = GetPhotosResponse(ResponseState.INITIAL, listOf())
            val errorResponse = GetPhotosResponse(ResponseState.ERROR, listOf())
            while (true) {
                try {
                    val source = when (roverName.lowercase()) {
                        "curiosity" -> apiService.getPhotosCuriosity(sol, camera)
                        "opportunity" -> apiService.getPhotosOpportunity(sol, camera)
                        else -> apiService.getPhotosSpirit(sol, camera)
                    }
                    if (source.isSuccessful) {
                        source.body()?.let {
                            finalResponse = GetPhotosResponse(if (it.photos.isEmpty()) ResponseState.EMPTY else ResponseState.FILLED, apiMapper.fromEntityList(it.photos))
                        } ?: run {
                            finalResponse = errorResponse
                        }
                    } else {
                        finalResponse = errorResponse
                    }
                } catch (e: Exception) {
                    Log.e("Failure", "Message " + e.message)
                    //TODO: Comparing against a specific error message isn't a reliable way to check whether or not the phone has internet access.
                    //  Using something like ConnectivityManager would be a better approach.
                    finalResponse = GetPhotosResponse(if (e.message == "Unable to resolve host \"api.nasa.gov\": No address associated with hostname") ResponseState.NO_INTERNET else ResponseState.ERROR, listOf())
                }
                emit(finalResponse)
                    if (finalResponse.status == ResponseState.INITIAL) {
                        delay(refreshIntervalMsShort)
                    } else {
                        delay(refreshIntervalMsLong)
                    }
            }
        }
    }
}