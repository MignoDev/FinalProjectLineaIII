package com.example.homefinance.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinance.Model.UserRequest
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateProfileScreen (navigateToLogin: () -> Unit, viewModel: UserViewModel = viewModel())
{
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickName by remember { mutableStateOf("") }

    //estado para controlar la visibilidad de la contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    //Estado para controlar el diálogo de confirmación
    var showDialog by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    //Estado para controlar la muestra de errores
    var formSubmitted by remember { mutableStateOf(false)}

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 103.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        Text(
            text = "Registro",
            style = MaterialTheme.typography.titleLarge
        )

        TextField(
            value = userName,
            onValueChange = { userName = it },
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
            onValueChange = { password = it },

            label = { Text("Contraseña") },

            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            isError = (formSubmitted && password.isBlank())
        )

        if (formSubmitted && password.isBlank()) {
            Text(
                text = "El nombre de usuario es obligatorio",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 2.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }

        TextField(
            value = nickName,
            onValueChange = { nickName = it },

            label = { Text("nombre de usuario") },

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        )

        Button(
            onClick = {
                if (userName.isNotEmpty() && password.isNotEmpty()) {
                    scope.launch {
                        if (userName.isNotEmpty() || password.isNotEmpty()) {
                            val nuevoUsuario = UserRequest(
                                userName = userName,
                                password = password,
                                nickName = if (nickName.isEmpty()) userName else nickName
                            )
                            viewModel.createUser(nuevoUsuario)

                            userName = ""
                            password = ""
                            nickName = ""
                            // Mostrar el Toast de éxito
                            Toast.makeText(context, "Usuario insertado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            enabled = userName.isNotEmpty() && password.isNotEmpty() // Habilitar solo si todo es válido
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Registrarse")

        }
        TextButton(navigateToLogin) {
            Text(text = "Iniciar sesión")
        }
    }
}