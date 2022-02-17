package com.example.data.network.model

import com.example.domain.Camera
import com.example.domain.Photo
import com.example.domain.Rover
import com.example.domain.utils.DomainMapper
import javax.inject.Inject

class PhotoApiMapper @Inject constructor() : DomainMapper<PhotoApi, Photo> {
    override fun mapToDomainModel(model: PhotoApi): Photo {
        return Photo(
            model.id,
            model.sol,
            Camera(
                model.camera.id,
                model.camera.name,
                model.camera.rover_id,
                model.camera.full_name
            ),
            model.img_src,
            model.earth_date,
            Rover(
                model.rover.id,
                model.rover.name,
                model.rover.landing_date,
                model.rover.launch_date,
                model.rover.status
            )
        )
    }

    override fun mapFromDomainModel(domainModel: Photo): PhotoApi {
        return PhotoApi(
            domainModel.id,
            domainModel.sol,
            CameraApi(
                domainModel.camera.id,
                domainModel.camera.name,
                domainModel.camera.rover_id,
                domainModel.camera.full_name
            ),
            domainModel.img_src,
            domainModel.earth_date,
            RoverApi(
                domainModel.rover.id,
                domainModel.rover.name,
                domainModel.rover.landing_date,
                domainModel.rover.launch_date,
                domainModel.rover.status
            )
        )
    }

    fun fromEntityList(initial: List<PhotoApi>): List<Photo> {
        return initial.map { mapToDomainModel(it) }
    }
}