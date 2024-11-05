package com.example.internet.data

import com.example.internet.network.InternetApi
import com.example.internet.network.RexistroRemoto

interface InternetRepository {
    suspend fun getDatosInternet(): List<RexistroRemoto>
}

class NetworkInternetRepository() : InternetRepository {
    override suspend fun getDatosInternet(): List<RexistroRemoto> {
        return InternetApi.retrofitService.getMovementos()
    }
}