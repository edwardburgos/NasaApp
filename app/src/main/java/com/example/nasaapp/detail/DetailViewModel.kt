package com.example.nasaapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.model.ResponseState
import com.example.domain.CameraName
import com.example.domain.Photo
import com.example.domain.RoverName
import com.example.usecases.getphotos.GetPhotosApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPhotosApi: GetPhotosApiUseCase
) : ViewModel() {

    private val _scrolled = MutableLiveData(false)
    val scrolled: LiveData<Boolean>
        get() = _scrolled

    fun updateScrolled() {
        _scrolled.value = true
    }

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _responseState = MutableLiveData(ResponseState.INITIAL)
    val responseState: LiveData<ResponseState>
        get() = _responseState

    private var currentFlow = viewModelScope.launch { }

    fun getPhotosFromFlow(name: RoverName, sol: String, selectedCamera: CameraName) {
        currentFlow.cancel()
        currentFlow = viewModelScope.launch {
            getPhotosApi(name, sol, selectedCamera).collect {
                if (_photos.value == null || _photos.value?.size == 0 || _photos.value != it.photos
                    || it.status != ResponseState.INITIAL
                ) {
                    _responseState.value = it.status
                    _photos.value = it.photos
                }
            }
        }
    }
}