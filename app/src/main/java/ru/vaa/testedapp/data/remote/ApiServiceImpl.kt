package ru.vaa.testedapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import ru.vaa.testedapp.data.cameras.CamerasModel
import ru.vaa.testedapp.data.doors.DoorsModel
import ru.vaa.testedapp.data.remote.dto.ApiResponse
import ru.vaa.testedapp.data.remote.dto.CamerasData

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getCameras(): ApiResponse<CamerasData<List<CamerasModel>>>? {
        return try {
            client.get(ApiRoutes.CAMERAS).body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getDoors(): ApiResponse<List<DoorsModel>>? {
        return try {
            client.get(ApiRoutes.DOORS).body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}