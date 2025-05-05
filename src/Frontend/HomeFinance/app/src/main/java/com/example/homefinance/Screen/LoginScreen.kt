package com.example.homefinance.Screen

import android.graphics.Color
import android.provider.CalendarContract
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.example.homefinance.ViewModel.UserViewModel

@Composable
fun LoginScreen (navigateToHome: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var formSubmitted by remember { mutableStateOf(false)}
    var passwordVisible by remember { mutableStateOf(false)}

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "LOGIN SCREEN", fontSize = 25.sp)
        TextField(
            value = userName,
            onValueChange = {userName = it},
            label = { Text("nombre de usuario") },

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            isError = (formSubmitted && userName.isBlank())
        )
        if (formSubmitted && userName.isBlank()) {
            Text(
                text = "El nombre de usuario es obligatorio",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 2.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }

        TextField(
            value = password,
            onValueChange = {password = it},

            label = { Text("Contraseña") },

            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            isError = ( formSubmitted && password.isBlank())
        )

        if (formSubmitted && password.isBlank()) {
            Text(
                text = "El nombre de usuario es obligatorio",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 2.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }

        Button(navigateToHome) {
            Text(text = "Ir a home")
        }
    }
}