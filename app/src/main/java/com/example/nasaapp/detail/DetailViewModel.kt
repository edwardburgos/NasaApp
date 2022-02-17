package com.example.nasaapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.model.PhotoApiMapper
import com.example.domain.Photo
import com.example.usecases.getphotos.GetPhotosApiUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPhotosApi: GetPhotosApiUseCaseImpl,
    private val apiMapper: PhotoApiMapper
) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        viewModelScope.launch {
            getPhotosApi("Curiosity", 1000).collect {
                if (_photos.value == null || _photos.value?.size == 0 || _photos.value != apiMapper.fromEntityList(it)) {
                    _photos.value = apiMapper.fromEntityList(it)
                }
            }
        }
    }
}