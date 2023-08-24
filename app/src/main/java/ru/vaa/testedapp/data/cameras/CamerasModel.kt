package ru.vaa.testedapp.data.cameras

import kotlinx.serialization.Serializable

@Serializable
data class CamerasModel(
    val name: String,
    val snapshot: String,
    val room: String? = null,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)
