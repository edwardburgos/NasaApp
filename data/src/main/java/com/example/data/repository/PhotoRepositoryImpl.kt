package com.example.data.repository

import android.util.Log
import com.example.data.network.ApiService
import com.example.data.network.model.ResponseApi
import com.example.data.repository.model.GetPhotosResponse
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

    override fun getPhotosFromApi(roverName: String, sol: String, selectedCamera: String): Flow<GetPhotosResponse> {
        val camera = if (selectedCamera == "All") null else selectedCamera.lowercase()
        return flow {
            val source = when (roverName.lowercase()) {
                "curiosity" -> apiService.getPhotosCuriosity(sol, camera)
                "opportunity" -> apiService.getPhotosOpportunity(sol, camera)
                else -> apiService.getPhotosSpirit(sol, camera)
            }
            var finalResponse = GetPhotosResponse("initial", listOf())

            // TODO: This is where using Retrofit's suspend function support would come in handy.
            //  You would have the ability to write asynchronous code in a synchronous way without having
            //  to call source.enqueue nor overriding onResponse and onFailure. It would make the overall code
            //  in the repository implementation cleaner.
            source.enqueue(object : Callback<ResponseApi> {
                override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            // TODO: Having a "status" field on the response is a really good approach, since you can let the UI know about the state
                            //  and act accordingly, but it would be better if the type of "status" was either an enum or a sealed class.
                            //  Having it as a hardcoded string makes it more error prone (a simple typo could break the functionality and would be hard to debug)
                            //  and less scalable if you later wanted to add another status.
                            finalResponse = GetPhotosResponse(if (it.photos.isEmpty()) "empty" else "filled", it.photos)
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                    Log.e("Failure", "Message " + t.message)
                    //TODO: Comparing against a specific error message isn't a reliable way to check whether or not the phone has internet access.
                    //  Using something like ConnectivityManager would be a better approach.
                    finalResponse = GetPhotosResponse(if (t.message == "Unable to resolve host \"api.nasa.gov\": No address associated with hostname") "no internet" else "error", listOf())
                }
            })
            while (true) {
                emit(finalResponse)
                    if (finalResponse.status == "initial") {
                        delay(refreshIntervalMsShort)
                    } else {
                        delay(refreshIntervalMsLong)
                    }
            }
        }
    }
}