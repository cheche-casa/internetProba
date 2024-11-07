package com.example.internet.network

import retrofit2.http.GET
import retrofit2.http.Query

interface InternetApiService {
    @GET("serial.ps1")
    suspend fun getSerial(): Serial

    @GET("le.ps1")
    suspend fun getMovementos(@Query("S") s: Long): List<RexistroRemoto>

    @GET("grava.ps1")
    suspend fun putMovementos(@Query("C") c: String): Serial
}

