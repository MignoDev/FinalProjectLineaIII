package com.example.homefinance.Screen

import android.R
import android.graphics.Color
import android.provider.CalendarContract
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.example.homefinance.Model.LoginRequest
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen (navigateToHome: (Long) -> Unit, navigateToCreateUser: () -> Unit, userViewModel: UserViewModel = viewModel()) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var formSubmitted by remember { mutableStateOf(false)}
    var passwordVisible by remember { mutableStateOf(false)}

    val loggedIn by userViewModel.loggedIn.observeAsState()
    val user by userViewModel.userUnique.observeAsState(null)

    val scope = rememberCoroutineScope()

    LaunchedEffect(loggedIn, user) {
        if (loggedIn == true && user != null) {
            navigateToHome(user!!.id)
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "Iniciar sesión", style = MaterialTheme.typography.titleLarge)
        TextField(
            value = userName,
            onValueChange = {userName = it},
            label = { Text("nombre de usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            isError = (formSubmitted && userName.isBlank()),
            singleLine = true
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
            singleLine = true,
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
                style = MaterialTheme.typography.labelSmall,
            )
        }

        Button(
            onClick = {
                formSubmitted = true
                if (userName.isNotBlank() && password.isNotBlank()) {
                    val loginRequest = LoginRequest(userName = userName, password = password)
                    userViewModel.login(loginRequest)
                    userViewModel.loginFindUer(userName) // <- importante mover esto aquí
                }
            }
        ) {
            Text(text = "Iniciar sesión")
        }

        TextButton(navigateToCreateUser) {
            Text(text = "Crear cuenta")
        }

    }
}