package ru.vaa.testedapp.repository

import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import ru.vaa.testedapp.data.cameras.CamerasModel
import ru.vaa.testedapp.data.doors.DoorsModel

interface MongoRepository {
    fun getCameras(): Flow<List<CamerasModel>>
    fun getDoors(): Flow<List<DoorsModel>>
    suspend fun <T> insert(value: T)
    suspend fun <T> update(value: T)
    suspend fun delete(id: ObjectId)
}