package com.example.nasaapp.home

import androidx.lifecycle.ViewModel

import androidx.lifecycle.*
import com.example.data.network.model.ResponseState
import com.example.domain.Camera
import com.example.domain.CameraName
import com.example.domain.Photo
import com.example.domain.RoverName
import com.example.usecases.getphotos.GetPhotosApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosApi: GetPhotosApiUseCase
) : ViewModel() {

    private val _responseState = MutableLiveData(ResponseState.INITIAL)
    val responseState: LiveData<ResponseState>
        get() = _responseState

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedPhoto = MutableLiveData<Int?>()
    val navigateToSelectedPhoto: LiveData<Int?>
        get() = _navigateToSelectedPhoto

    private val _sol = MutableLiveData("1000")
    val sol: LiveData<String>
        get() = _sol

    private val _roverName = MutableLiveData(RoverName.Curiosity)
    val roverName: LiveData<RoverName>
        get() = _roverName

    private val _selectedCamera = MutableLiveData(CameraName.All)
    val selectedCamera: LiveData<CameraName>
        get() = _selectedCamera

    fun updateSol(newValue: String) {
        _sol.value = newValue
    }

    fun updateRoverName(newValue: RoverName) {
        _roverName.value = newValue
    }

    fun updateSelectedCamera(newValue: CameraName) {
        _selectedCamera.value = newValue
        _sol.value?.let {
            _roverName.value?.let { roverName ->
                _selectedCamera.value?.let { selectedCamera ->
                    getPhotosFromFlow(roverName, it, selectedCamera)
                }
            }
        }
    }

    private var currentFlow = viewModelScope.launch { }

    init {
        _sol.value?.let {
            _roverName.value?.let { roverName ->
                _selectedCamera.value?.let { selectedCamera ->
                    getPhotosFromFlow(roverName, it, selectedCamera)
                }
            }
        }
    }

    fun getPhotosFromFlow(name: RoverName, sol: String, selectedCamera: CameraName) {
        currentFlow.cancel()
        currentFlow = viewModelScope.launch {
            getPhotosApi(name, sol, selectedCamera).collect {
                if (_photos.value == null || _photos.value?.size == 0 ||
                    _photos.value != it.photos ||
                    it.status != ResponseState.INITIAL
                ) {
                    _responseState.value = it.status
                    _photos.value = it.photos
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

    fun getAvailableCameras(roverName: RoverName): List<CameraName> {
        val cameras = when (roverName) {
            RoverName.Curiosity -> listOf(CameraName.All, CameraName.FHAZ, CameraName.RHAZ, CameraName.MAST, CameraName.CHEMCAM, CameraName.MAHLI, CameraName.MARDI, CameraName.NAVCAM)
            RoverName.Opportunity, RoverName.Spirit -> listOf(CameraName.All, CameraName.FHAZ, CameraName.RHAZ, CameraName.NAVCAM, CameraName.PANCAM, CameraName.MINITES)
            else -> listOf()
        }
        if (cameras.indexOf(selectedCamera.value) == -1) {
            _selectedCamera.value = CameraName.All
            updateSelectedCamera(CameraName.All)
        }
        return cameras
    }
}