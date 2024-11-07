package com.example.internet.data

import com.example.internet.network.InternetApiService
import com.example.internet.network.RexistroRemoto

interface InternetRepository {
    suspend fun getDatosInternet(serial: Long): List<RexistroRemoto>
}

class NetworkInternetRepository(
    private val internetApiService: InternetApiService
) : InternetRepository {
    override suspend fun getDatosInternet(serial: Long): List<RexistroRemoto> =
        internetApiService.getMovementos(serial)
}