package com.example.data.repository

import android.util.Log
import com.example.data.network.ApiService
import com.example.data.network.model.PhotoApiMapper
import com.example.data.network.model.ResponseApi
import com.example.data.network.model.ResponseState
import com.example.data.repository.model.GetPhotosResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            val source = when (roverName.lowercase()) {
                "curiosity" -> apiService.getPhotosCuriosity(sol, camera)
                "opportunity" -> apiService.getPhotosOpportunity(sol, camera)
                else -> apiService.getPhotosSpirit(sol, camera)
            }
            var finalResponse = GetPhotosResponse(ResponseState.INITIAL, listOf())

            // TODO: This is where using Retrofit's suspend function support would come in handy.
            //  You would have the ability to write asynchronous code in a synchronous way without having
            //  to call source.enqueue nor overriding onResponse and onFailure. It would make the overall code
            //  in the repository implementation cleaner.
            source.enqueue(object : Callback<ResponseApi> {
                override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            finalResponse = GetPhotosResponse(if (it.photos.isEmpty()) ResponseState.EMPTY else ResponseState.FILLED, apiMapper.fromEntityList(it.photos))
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                    Log.e("Failure", "Message " + t.message)
                    //TODO: Comparing against a specific error message isn't a reliable way to check whether or not the phone has internet access.
                    //  Using something like ConnectivityManager would be a better approach.
                    finalResponse = GetPhotosResponse(if (t.message == "Unable to resolve host \"api.nasa.gov\": No address associated with hostname") ResponseState.NO_INTERNET else ResponseState.ERROR, listOf())
                }
            })
            while (true) {
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