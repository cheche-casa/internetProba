package com.example.internet.network

import retrofit2.http.GET
import retrofit2.http.Query

interface InternetApiService {
    @GET("serial.ps1")
    suspend fun getSerial(): String

    @GET("le.ps1")
    suspend fun getMovementos(@Query("S") s: Long): List<RexistroRemoto>
}

