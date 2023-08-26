package ru.vaa.testedapp.data.cameras

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.vaa.testedapp.data.remote.ApiService
import ru.vaa.testedapp.repository.MongoRepository
import ru.vaa.testedapp.repository.model.Camera
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {

    private val tag = CamerasViewModel::class.java.simpleName
    var loadProgress = mutableStateOf(false)
    private val apiService by lazy { ApiService.create() }

    private val _listCameras = MutableLiveData<List<Camera>>()
    val listCameras: LiveData<List<Camera>>
        get() = _listCameras

    init {
        viewModelScope.launch {
            repository.getCameras().collect {
                if (it.isEmpty()) {
                    getCameras()
                }
                _listCameras.value = it
            }
        }
    }


    private fun getCameras() {
        loadProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getCameras()
            if (response?.success == true) {
                response.data.cameras.forEach {
                    repository.insert(Camera().apply {
                        name = it.name
                        snapshot = it.snapshot
                        favorites = it.favorites
                        room = it.room
                        rec = it.rec
                    })
                }
                withContext(Dispatchers.Main) { loadProgress.value = false }
            } else {
                withContext(Dispatchers.Main) { loadProgress.value = false }
                Log.d(tag, "Error response")
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAll()
        }
        _listCameras.value = null
    }
}