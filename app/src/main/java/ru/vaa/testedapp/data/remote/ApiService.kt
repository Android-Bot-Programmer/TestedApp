package ru.vaa.testedapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.vaa.testedapp.data.cameras.CamerasModel
import ru.vaa.testedapp.data.doors.DoorsModel
import ru.vaa.testedapp.data.remote.dto.ApiResponse
import ru.vaa.testedapp.data.remote.dto.CamerasData

interface ApiService {

    suspend fun getCameras(): ApiResponse<CamerasData<List<CamerasModel>>>?
    suspend fun getDoors(): ApiResponse<List<DoorsModel>>?

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            encodeDefaults = false
                            ignoreUnknownKeys = true
                            isLenient = true
                            useAlternativeNames = false
                        })
                    }
                }
            )
        }
    }
}