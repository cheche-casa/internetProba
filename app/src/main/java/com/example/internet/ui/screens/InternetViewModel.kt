package com.example.internet.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internet.network.InternetApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface InternetUiState {
    data class Success(val movementos: String) : InternetUiState
    object Error : InternetUiState
    object Loading : InternetUiState
}

class InternetViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var internetUiState: InternetUiState by mutableStateOf(InternetUiState.Loading)
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
        viewModelScope.launch {
            try {
                val listResult = InternetApi.retrofitService.getMovementos()
                internetUiState = InternetUiState.Success(listResult)
            }
            catch (e: IOException) {
                internetUiState = InternetUiState.Error
            }
        }
    }
}