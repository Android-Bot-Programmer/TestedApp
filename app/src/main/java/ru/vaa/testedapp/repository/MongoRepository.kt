package ru.vaa.testedapp.repository

import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import ru.vaa.testedapp.repository.model.Camera
import ru.vaa.testedapp.repository.model.Door

interface MongoRepository {
    fun getCameras(): Flow<List<Camera>>
    fun getDoors(): Flow<List<Door>>
    suspend fun insert(value: RealmObject)
    suspend fun updateCameraName(value: Camera)
    suspend fun updateDoorName(value: Door)
    suspend fun deleteDoor(id: ObjectId)
}