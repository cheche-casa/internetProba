package com.example.internet.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.internet.network.RexistroRemoto
import kotlin.reflect.KFunction1

@Composable
fun HomeScreen(
    internetUiState: InternetUiState,
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    retryAction: KFunction1<Long, Unit>
    ) {

    val resultado: List<RexistroRemoto>
    resultado = when (internetUiState) {
        is InternetUiState.Loading -> listOf(RexistroRemoto(1," ... Cargando ..."))
        is InternetUiState.Success -> internetUiState.movementos
        is InternetUiState.Error -> listOf(RexistroRemoto(1," ... Sen conexión ..."))
    }
    val updatedList: List<RexistroRemoto> = resultado
    ResultScreen(
        updatedList,
        modifier.padding(top = contentPadding.calculateTopPadding()),
        retryAction = retryAction
    )
}

@Composable
fun ResultScreen(
    strings: List<RexistroRemoto>,
    modifier: Modifier = Modifier,
    retryAction: KFunction1<Long, Unit>
){
    var text by remember { mutableStateOf("") }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // LazyColumn en la mitad superior
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(strings) { string ->
                    Text(text = string.comando, modifier = Modifier.padding(8.dp))
                }
            }

            // TextField a continuación
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Introduce o comando") }
            )

            // Dos botones en la parte inferior
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { retryAction(text.toLongOrNull() ?: 0L) }) {
                    Text("Refresca")
                }
                Button(onClick = { /* Acción del segundo botón */ }) {
                    Text("Grava")
                }
            }
        }
    }
}
