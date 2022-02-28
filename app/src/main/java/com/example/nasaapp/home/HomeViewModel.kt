package com.example.nasaapp.home

import androidx.lifecycle.ViewModel

import androidx.lifecycle.*
import com.example.data.network.model.ResponseState
import com.example.domain.Photo
import com.example.usecases.getphotos.GetPhotosApiUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosApi: GetPhotosApiUseCaseImpl
) : ViewModel() {

    // TODO: These could be in an enum class, a sealed class, or a constant instead of being hardcoded strings
    val rovers = listOf("Curiosity", "Opportunity", "Spirit")

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

    private val _roverName = MutableLiveData("Curiosity")
    val roverName: LiveData<String>
        get() = _roverName

    private val _selectedCamera = MutableLiveData("All")
    val selectedCamera: LiveData<String>
        get() = _selectedCamera

    fun updateSol(newValue: String) {
        _sol.value = newValue
    }

    fun updateRoverName(newValue: String) {
        _roverName.value = newValue
    }

    fun updateSelectedCamera(newValue: String) {
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

    fun getPhotosFromFlow(name: String, sol: String, selectedCamera: String) {
        currentFlow.cancel()
        currentFlow = viewModelScope.launch {
            getPhotosApi(name.lowercase(), sol, selectedCamera).collect {
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

    fun getAvailableCameras(roverName: String): List<String> {
        val cameras = when (roverName) {
            //TODO: These kind of string values should be in either a constant or a string resource inside strings.xml
            "Curiosity" -> listOf("All", "FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM")
            "Opportunity", "Spirit" -> listOf("All", "FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES")
            else -> listOf()
        }
        if (cameras.indexOf(selectedCamera.value) == -1) {
            _selectedCamera.value = "All"
            updateSelectedCamera("All")
        }
        return cameras
    }
}