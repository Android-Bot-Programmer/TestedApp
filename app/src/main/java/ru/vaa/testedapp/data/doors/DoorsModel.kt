package ru.vaa.testedapp.data.doors

import kotlinx.serialization.Serializable

@Serializable
data class DoorsModel(
    val name: String,
    val snapshot: String? = null,
    val room: String?,
    val id: Int,
    val favorites: Boolean
)
