package ru.vaa.testedapp.data.cameras

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService

class CamerasViewModel: ViewModel() {
    private val tag = CamerasViewModel::class.java.simpleName
    private val apiService by lazy {
        ApiService.create()
    }

    fun getCameras() {
        viewModelScope.launch {
            Log.d(tag, apiService.getCameras().toString())
        }
    }
}