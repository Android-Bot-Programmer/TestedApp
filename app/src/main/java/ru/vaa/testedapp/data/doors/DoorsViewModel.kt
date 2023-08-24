package ru.vaa.testedapp.data.doors

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService

class DoorsViewModel: ViewModel() {
    private val tag = DoorsViewModel::class.java.simpleName
    private val apiService by lazy {
        ApiService.create()
    }

    fun getDoors() {
        viewModelScope.launch {
            Log.d(tag, apiService.getDoors().toString())
        }
    }
}