package com.example.homefinance.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinance.Model.User
import com.example.homefinance.Model.UserRequest
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users by viewModel.user.observeAsState(emptyList())
    var searchValue by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickName by remember { mutableStateOf("") }

    //estado para controlar la visibilidad de la contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    //Estado para controlar el diálogo de confirmación
    var showDialog by remember { mutableStateOf(false) }
    var usuarioAEliminar by remember { mutableStateOf<User?>(null) }

    //Estado para controlar la muestra de errores
    var formSubmitted by remember { mutableStateOf(false)}

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.listUsers()
    }

    val usuariosFiltrados = users?.filter {
        it.userName.toString().contains(searchValue, ignoreCase = true) ||
        it.nickName.toString().contains(searchValue, ignoreCase = true)
    } ?: emptyList()

    Column (modifier = Modifier.padding(top = 60.dp)) {
        Text(text = "Lista de usuarios", style = MaterialTheme.typography.headlineSmall)

        TextField(
            value = searchValue,
            onValueChange = {searchValue = it},
            label = { Text("Buscar por código") },
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "Codigo Icon")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

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

        TextField(
            value = nickName,
            onValueChange = {nickName = it},

            label = { Text("nombre de usuario") },

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        )

        Button(
            onClick = {
                if (userName.isNotEmpty() && password.isNotEmpty()) {
                    scope.launch {
                        formSubmitted = true
                        if (userName.isNotEmpty() || password.isNotEmpty())
                        {
                            val nuevoUsuario = UserRequest(userName = userName, password = password, nickName = if(nickName.isEmpty()) userName else nickName)
                            viewModel.createUser(nuevoUsuario)
                            userName = ""
                            password = ""
                            nickName = ""
                            // Mostrar el Toast de éxito
                            Toast.makeText(context, "Usuario insertado", Toast.LENGTH_SHORT).show()
                            viewModel.listUsers()
                        }
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            enabled = userName.isNotEmpty() && password.isNotEmpty() // Habilitar solo si todo es válido
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar Estudiante Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Agregar Estudiante")
        }

        if (usuariosFiltrados.isEmpty()) {
            Text(text = "No hay estudiantes disponibles.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(usuariosFiltrados) { usuario ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Usuario: ${usuario.userName}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Alias: ${usuario.nickName}",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            // Botón de Eliminar Estudiante con ícono
                            Button(
                                onClick = {
                                    // Mostrar el diálogo de confirmación de eliminación
                                    usuarioAEliminar = usuario
                                    showDialog = true
                                },
                                modifier = Modifier.padding(top = 8.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = "Eliminar Estudiante Icon"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Eliminar")
                            }
                        }
                    }
                }
            }
        }

    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmación") },
            text = { Text("¿Está seguro que desea eliminar a este usuario?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Eliminar el estudiante si el usuario confirma
                        usuarioAEliminar?.let {
                            viewModel.deleteUser(it.id)
                            // Mostrar el Toast de éxito
                            Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                            viewModel.listUsers()
                        }
                        showDialog = false
                    }
                ) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("No")
                }
            }
        )
    }


}