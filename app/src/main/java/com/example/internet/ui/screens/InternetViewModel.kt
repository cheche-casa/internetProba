package com.example.internet.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InternetViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var internetUiState: String by mutableStateOf("")
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getDatosInternet()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getDatosInternet() {
        internetUiState = "Set the Mars API status response here!"
    }
}