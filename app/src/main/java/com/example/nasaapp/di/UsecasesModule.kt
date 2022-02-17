package com.example.nasaapp.di

import com.example.usecases.getphotos.GetPhotosApiUseCase
import com.example.usecases.getphotos.GetPhotosApiUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsecasesModule {
    @Provides
    fun providesGetPhotosApiUseCaseImpl(getPhotosApiUseCaseImpl: GetPhotosApiUseCaseImpl): GetPhotosApiUseCase {
        return getPhotosApiUseCaseImpl
    }
}