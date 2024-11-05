package com.example.internet.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.16:8888"
val json = Json {
    ignoreUnknownKeys = true // Ignora las claves desconocidas en el JSON
}
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface InternetApiService {
    @GET("serial.ps1")
    suspend fun getSerial(): String

    @GET("le.ps1?S=0")
    suspend fun getMovementos(): List<RexistroRemoto>
}

object InternetApi {
    val retrofitService : InternetApiService by lazy {
        retrofit.create(InternetApiService::class.java)
    }
}