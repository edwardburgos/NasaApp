package com.example.nasaapp.home

import androidx.lifecycle.ViewModel

import androidx.lifecycle.*
import com.example.data.network.model.PhotoApiMapper
import com.example.domain.Photo
import com.example.usecases.getphotos.GetPhotosApiUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosApi: GetPhotosApiUseCaseImpl,
    private val apiMapper: PhotoApiMapper
) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedPhoto = MutableLiveData<Int?>()
    val navigateToSelectedPhoto: LiveData<Int?>
        get() = _navigateToSelectedPhoto

    init {

        viewModelScope.launch {
            getPhotosApi("Curiosity", 1000).collect {
                if (_photos.value == null || _photos.value?.size == 0 || _photos.value != apiMapper.fromEntityList(it)) {
                    _photos.value = apiMapper.fromEntityList(it)
                }
            }
        }
    }

    fun displayPropertyDetails(photoIndex: Int) {
        _navigateToSelectedPhoto.value = photoIndex
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}