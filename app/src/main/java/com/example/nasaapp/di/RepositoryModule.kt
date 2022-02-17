package com.example.nasaapp.di

import com.example.data.repository.PhotoRepository
import com.example.data.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesPhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl): PhotoRepository {
        return photoRepositoryImpl
    }
}