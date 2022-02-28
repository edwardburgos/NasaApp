package com.example.usecases.getphotos

import com.example.data.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosApiUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository) :
    GetPhotosApiUseCase {
    override operator fun invoke(roverName: String, sol: String, selectedCamera: String) = photoRepository.getPhotosFromApi(roverName, sol, selectedCamera)
}