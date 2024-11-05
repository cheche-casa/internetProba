package com.example.internet.network

import kotlinx.serialization.Serializable

@Serializable
data class RexistroRemoto(
    val serial: Long,  val comando: String
)