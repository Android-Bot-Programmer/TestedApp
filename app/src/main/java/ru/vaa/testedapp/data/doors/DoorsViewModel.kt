package ru.vaa.testedapp.data.doors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService

class DoorsViewModel : ViewModel() {
    private val tag = DoorsViewModel::class.java.simpleName
    private val apiService by lazy {
        ApiService.create()
    }
    private val _listDoors = MutableLiveData<List<DoorsModel>>()
    val listDoors: LiveData<List<DoorsModel>>
        get() = _listDoors

    fun getDoors() {
        viewModelScope.launch {
            _listDoors.value = apiService.getDoors()?.data
            Log.d(tag, listDoors.value.toString())
        }
    }
}