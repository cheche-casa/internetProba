package com.example.internet.network

import retrofit2.http.GET

interface InternetApiService {
    @GET("serial.ps1")
    suspend fun getSerial(): String

    @GET("le.ps1?S=0")
    suspend fun getMovementos(): List<RexistroRemoto>
}

