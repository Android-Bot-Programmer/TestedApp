package ru.vaa.testedapp.data.cameras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService
import ru.vaa.testedapp.repository.MongoRepository
import ru.vaa.testedapp.repository.model.Camera
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {
    private val apiService by lazy { ApiService.create() }
    private val _listCameras = MutableLiveData<List<Camera>>()
    val listCameras: LiveData<List<Camera>>
        get() = _listCameras

    init {
        viewModelScope.launch {
            repository.getCameras().collect {
                if (it.isEmpty()) {
                    getCameras()
                } else {
                    _listCameras.value = it
                }
            }
        }
    }


    private fun getCameras() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_listCameras.value.isNullOrEmpty()) {
                val data = apiService.getCameras()?.data?.cameras
                data?.forEach {
                    repository.insert(Camera().apply {
                        name = it.name
                        snapshot = it.snapshot
                        favorites = it.favorites
                        room = it.room
                        rec = it.rec
                    })
                }
            }
        }
    }
}