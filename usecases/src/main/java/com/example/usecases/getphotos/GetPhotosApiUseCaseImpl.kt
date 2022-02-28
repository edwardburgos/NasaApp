package com.example.usecases.getphotos

import com.example.data.repository.PhotoRepository
import com.example.domain.CameraName
import com.example.domain.RoverName
import javax.inject.Inject

class GetPhotosApiUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository) :
    GetPhotosApiUseCase {
    override operator fun invoke(roverName: RoverName, sol: String, selectedCamera: CameraName) = photoRepository.getPhotosFromApi(roverName, sol, selectedCamera)
}