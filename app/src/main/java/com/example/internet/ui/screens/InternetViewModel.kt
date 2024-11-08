package com.example.internet.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.internet.InternetApplication
import com.example.internet.data.InternetRepository
import com.example.internet.network.RexistroRemoto
import com.example.internet.network.Serial
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface InternetUiState {
    data class Success(val movementos: List<RexistroRemoto>) : InternetUiState
    object Error : InternetUiState
    object Loading : InternetUiState
}
sealed interface InternetUiStateSerial {
    data class Success(val serial: Serial) : InternetUiStateSerial
    object Error : InternetUiStateSerial
    object Loading : InternetUiStateSerial
}

class InternetViewModel(
    private val internetRepository: InternetRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var internetUiState: InternetUiState by mutableStateOf(InternetUiState.Loading)
        private set
    var internetUiStateSerial: InternetUiStateSerial by mutableStateOf(InternetUiStateSerial.Loading)
        private set


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getSerial()
        getDatosInternet(serial = 0)
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getDatosInternet(serial: Long) {
        viewModelScope.launch {
            try {
                val listResult = internetRepository.getDatosInternet(serial)
                internetUiState = InternetUiState.Success(
                    listResult
                )
            }
            catch (e: IOException) {
                internetUiState = InternetUiState.Error
            }
        }
    }

    fun getSerial(){
        viewModelScope.launch {
            try {
                val serialResult = internetRepository.getSerial()
                internetUiStateSerial = InternetUiStateSerial.Success(
                    serialResult
                )
            }
            catch (e: IOException) {
                internetUiStateSerial = InternetUiStateSerial.Error
            }
        }
    }

    fun putDatosInternet(comando: String){
        viewModelScope.launch {
            try {
                val serialResult = internetRepository.putDatosInternet(comando)
                internetUiStateSerial = InternetUiStateSerial.Success(
                    serialResult
                )
            }
            catch (e: IOException) {
                internetUiStateSerial = InternetUiStateSerial.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as InternetApplication)
                val internetRepository = application.container.internetRepository
                InternetViewModel(internetRepository = internetRepository)
            }
        }
    }
}