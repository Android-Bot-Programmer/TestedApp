package ru.vaa.testedapp.repository

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import ru.vaa.testedapp.repository.model.Camera
import ru.vaa.testedapp.repository.model.Door
import java.lang.Exception

class MongoRepositoryImpl(val realm: Realm) : MongoRepository {
    override fun getCameras(): Flow<List<Camera>> {
        return realm.query<Camera>().asFlow().map { it.list }
    }

    override fun getDoors(): Flow<List<Door>> {
        return realm.query<Door>().asFlow().map { it.list }
    }

    override suspend fun insert(value: RealmObject) {
        realm.write { copyToRealm(value) }
    }

    override suspend fun updateCameraName(value: Camera) {
        realm.write {
            val queriedObject = query<Camera>(query = "_id == $0", value._id).first().find()
            queriedObject?.name = value.name
        }
    }

    override suspend fun updateDoorName(value: Door) {
        realm.write {
            val queriedObject = query<Door>(query = "_id == $0", value._id).first().find()
            queriedObject?.name = value.name
        }
    }

    override suspend fun deleteDoor(id: ObjectId) {
        realm.write {
            val door = query<Door>(query = "_id == $0", id).first().find()
            try {
                door?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }
}