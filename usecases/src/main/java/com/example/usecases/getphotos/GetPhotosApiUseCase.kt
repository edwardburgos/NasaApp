package com.example.usecases.getphotos

import com.example.data.repository.model.GetPhotosResponse
import com.example.domain.CameraName
import com.example.domain.RoverName
import kotlinx.coroutines.flow.Flow

// TODO: Think of use cases as actions the user can trigger, while this is a completely valid use case
//  it shouldn't be the only one, since filtering/searching could also be use cases.
interface GetPhotosApiUseCase {
    operator fun invoke(roverName: RoverName, sol: String, selectedCamera: CameraName): Flow<GetPhotosResponse>
}