package ru.vaa.testedapp.data.doors

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
import org.mongodb.kbson.ObjectId
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

    private fun getDoors() {
        loadProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
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
                withContext(Dispatchers.Main) { loadProgress.value = false }
            } else {
                withContext(Dispatchers.Main) { loadProgress.value = false }
                Log.d(tag, "Error response")
            }
        }
    }

    fun updateDoorName(objectId: ObjectId, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.toString().isNotEmpty()) {
                repository.updateDoorName(value = Door().apply {
                    _id = ObjectId(hexString = objectId.toHexString())
                    name = value
                })
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAll()
        }
        _listDoors.value = null
    }
}