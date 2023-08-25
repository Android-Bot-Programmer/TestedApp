package ru.vaa.testedapp.repository.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Camera : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var name: String = ""
    var snapshot: String = ""
    var room: String? = null
    var favorites: Boolean = false
    var rec: Boolean = false
}

class Door : RealmObject {
    var _id: ObjectId = ObjectId.invoke()
    var name: String = ""
    var snapshot: String? = null
    var room: String? = null
    var favorites: Boolean = false
}