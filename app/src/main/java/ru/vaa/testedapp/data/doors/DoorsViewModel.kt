package ru.vaa.testedapp.data.doors

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.vaa.testedapp.data.remote.ApiService
import ru.vaa.testedapp.repository.MongoRepository
import ru.vaa.testedapp.repository.model.Door
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {

    private val tag = DoorsViewModel::class.java.simpleName
    var loadProgress = mutableStateOf(false)

    private val apiService by lazy { ApiService.create() }
    private val _listDoors = MutableLiveData<List<Door>>()
    val listDoors: LiveData<List<Door>>
        get() = _listDoors

    init {
        viewModelScope.launch {
            repository.getDoors().collect {
                if (it.isEmpty()) {
                    getDoors()
                }
                _listDoors.value = it
            }
        }
    }

    private suspend fun getDoors() {
        loadProgress.value = true
        val response = apiService.getDoors()

        if (response?.success == true) {
            response.data.forEach {
                repository.insert(Door().apply {
                    name = it.name
                    snapshot = it.snapshot
                    favorites = it.favorites
                    room = it.room
                })
            }
            loadProgress.value = false
        } else {
            loadProgress.value = false
            Log.d(tag, "Error response")
        }
    }

    suspend fun clearAll() {
        repository.clearAll()
        _listDoors.value = null
    }
}