package com.example.internet.data

import com.example.internet.network.InternetApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val internetRepository: InternetRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://192.168.0.16:8888"
    val json = Json {
        ignoreUnknownKeys = true // Ignora las claves desconocidas en el JSON
    }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : InternetApiService by lazy {
        retrofit.create(InternetApiService::class.java)
    }
    override val internetRepository: InternetRepository by lazy {
        NetworkInternetRepository(retrofitService)
    }
}