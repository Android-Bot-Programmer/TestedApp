package ru.vaa.testedapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T
)

@Serializable
data class CamerasData<T> (
    val room: List<String>,
    val cameras: T
)