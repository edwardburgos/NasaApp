package com.example.usecases.getphotos

import com.example.data.repository.PhotoRepositoryImpl
import javax.inject.Inject

class GetPhotosApiUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepositoryImpl) :
    GetPhotosApiUseCase {
    override operator fun invoke(roverName: String, sol: String, selectedCamera: String) = photoRepository.getPhotosFromApi(roverName, sol, selectedCamera)
}