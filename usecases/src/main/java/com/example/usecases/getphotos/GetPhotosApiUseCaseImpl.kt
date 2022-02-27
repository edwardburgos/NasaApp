package com.example.usecases.getphotos

import com.example.data.repository.PhotoRepositoryImpl
import javax.inject.Inject

// TODO: The interface of the repository should be passed as a dependency instead of the implementation itself,
//  this is to avoid hard-coupling the use case with the implementation, since the use case shouldn't really care
//  about the implementation of the repository.
class GetPhotosApiUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepositoryImpl) :
    GetPhotosApiUseCase {
    override operator fun invoke(roverName: String, sol: String, selectedCamera: String) = photoRepository.getPhotosFromApi(roverName, sol, selectedCamera)
}