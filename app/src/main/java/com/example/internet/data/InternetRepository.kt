package com.example.internet.data

import com.example.internet.network.InternetApiService
import com.example.internet.network.RexistroRemoto
import com.example.internet.network.Serial

interface InternetRepository {
    suspend fun getSerial(): Serial
    suspend fun getDatosInternet(serial: Long): List<RexistroRemoto>
    suspend fun putDatosInternet(comando: String): Serial
}

class NetworkInternetRepository(
    private val internetApiService: InternetApiService
) : InternetRepository {
    override suspend fun getSerial(): Serial =
        internetApiService.getSerial()
    override suspend fun getDatosInternet(serial: Long): List<RexistroRemoto> =
        internetApiService.getMovementos(serial)
    override suspend fun putDatosInternet(comando: String): Serial =
        internetApiService.putMovementos(comando)
}