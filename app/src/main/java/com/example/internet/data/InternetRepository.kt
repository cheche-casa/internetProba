package com.example.internet.data

import com.example.internet.network.InternetApiService
import com.example.internet.network.RexistroRemoto

interface InternetRepository {
    suspend fun getDatosInternet(): List<RexistroRemoto>
}

class NetworkInternetRepository(
    private val internetApiService: InternetApiService
) : InternetRepository {
    override suspend fun getDatosInternet(): List<RexistroRemoto> =
        internetApiService.getMovementos()
}