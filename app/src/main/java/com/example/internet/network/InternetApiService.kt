package com.example.internet.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.23.16:8888"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface InternetApiService {
    @GET("serial.ps1")
    suspend fun getSerial(): String
}

object InternetApi {
    val retrofitService : InternetApiService by lazy {
        retrofit.create(InternetApiService::class.java)
    }
}