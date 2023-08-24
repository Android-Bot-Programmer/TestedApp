package ru.vaa.testedapp.repository

import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import ru.vaa.testedapp.data.cameras.CamerasModel
import ru.vaa.testedapp.data.doors.DoorsModel

class MongoRepositoryImpl(val realm: Realm): MongoRepository {
    override fun getCameras(): Flow<List<CamerasModel>> {
        TODO("Not yet implemented")
    }

    override fun getDoors(): Flow<List<DoorsModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun <T> insert(value: T) {
        TODO("Not yet implemented")
    }

    override suspend fun <T> update(value: T) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: ObjectId) {
        TODO("Not yet implemented")
    }
}