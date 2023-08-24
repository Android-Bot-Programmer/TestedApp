package ru.vaa.testedapp.data.cameras

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService

class CamerasViewModel : ViewModel() {
    private val tag = CamerasViewModel::class.java.simpleName
    private val apiService by lazy {
        ApiService.create()
    }
    private val _listCameras = MutableLiveData<List<CamerasModel>>()
    val listCameras: LiveData<List<CamerasModel>>
        get() = _listCameras

    fun getCameras() {
        viewModelScope.launch {
            _listCameras.value = apiService.getCameras()?.data?.cameras
            Log.d(tag, listCameras.value.toString())
        }
    }
}