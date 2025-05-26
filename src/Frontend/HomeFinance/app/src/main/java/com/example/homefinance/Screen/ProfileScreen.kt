package com.example.homefinance.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinance.Model.HomeCreate
import com.example.homefinance.Model.HomeUserCreate
import com.example.homefinance.Model.User
import com.example.homefinance.Model.UserRequest
import com.example.homefinance.ViewModel.HomeUserViewModel
import com.example.homefinance.ViewModel.HomeViewModel
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Composable
fun ProfileScreen (
    userName: Long,
    viewModelHU: HomeUserViewModel = viewModel(),
    viewModelHome: HomeViewModel = viewModel(),
    viewModelUser: UserViewModel = viewModel()
    ) {

    val homeUser by viewModelHU.homeUserUnique.observeAsState(null)
    val home by viewModelHome.homeUnique.observeAsState(null)
    val user by viewModelUser.userUnique.observeAsState(null)
    val homeId by viewModelHome.homeId.observeAsState(null)
    val userInvited by viewModelUser.userInvite.observeAsState(null)

    var createHome by remember { mutableStateOf(false) }
    var joinHome by remember { mutableStateOf(false) }

    var homeName by remember { mutableStateOf("") }
    var inviteUser by remember { mutableStateOf("") }
    var editUserName by remember { mutableStateOf(false)}
    var userNameInput by remember { mutableStateOf("")}

    var inviteTriggered by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(userName) {
        viewModelUser.findUser(userName)
    }

// Cuando user cambie, carga homeUser
    LaunchedEffect(user) {
        user?.let { safeUser ->
            viewModelHU.findHomeUserByUserId(safeUser.id)
        }
    }

// Cuando homeUser cambie, carga home
    LaunchedEffect(homeUser) {
        homeUser?.let { safeHomeUser ->
            viewModelHome.findHome(safeHomeUser.homeId)
        }
    }

    LaunchedEffect(userInvited) {
        if (inviteTriggered && userInvited != null) {
            val homeUser = HomeUserCreate(
                homeId = homeUser!!.homeId,
                userId = userInvited!!.id,
                userRole = 2
            )
            viewModelHU.createHomeUser(homeUser)
            Toast.makeText(context, "Se ha unido al hogar", Toast.LENGTH_SHORT).show()
            inviteUser = ""
            createHome = false
            inviteTriggered = false
            joinHome = false
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 40.dp)
                .background(color = Color(0xff444499))
        ) {
            if (user != null) {
                if (editUserName)
                {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        TextField(
                            value = userNameInput,
                            onValueChange = { userNameInput = it },
                            label = { Text(text = "Nombre de usuario") },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Button(
                            onClick = {
                                if (userNameInput.isNotEmpty())
                                {
                                    val userRequest = User(id = user!!.id,userName = user!!.userName,
                                        password = user!!.password, nickName = userNameInput)
                                    viewModelUser.updateUser(user!!.id, userRequest)
                                    editUserName = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White // Cambia el fondo del botón
                            )

                        ) {
                            Text(text = "Cambiar nombre")
                        }
                    }

                } else {
                    Text(
                        text = user!!.nickName,
                        style = TextStyle(fontSize = 22.sp, color = Color(0xffffffff)),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    )

                    TextButton(
                        onClick = {
                            userNameInput = user!!.nickName
                            editUserName = true
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color(0x33ffffff),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }
            } else {
                // Puedes mostrar un texto de carga o un indicador visual
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                )
            }


        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {
            if (home != null){
                item {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 4.dp)
                    )
                    {
                        Text(
                            text = "Hogar",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        )
                        Row {
                            //Text(text = home!!.description)
                            Text(text = "Hogar seleccionado de prueba")
                        }
                    }
                }

                item {
                    Button(
                        onClick = {
                            joinHome = !joinHome
                        }
                    ) {
                        Text(text = "Invitar a hogar")
                    }
                }

                if (joinHome)
                {
                    item {
                        TextField(
                            value = inviteUser,
                            onValueChange = { inviteUser = it },
                            label = { Text(text = "Nombre del usuario") },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Button(
                            onClick = {
                                inviteTriggered = true
                                viewModelUser.findUserName(inviteUser)
                            }
                        ) {
                            Text(text = "Invitar")
                        }
                    }
                }
            } else
            {
                item {
                    Button(
                        onClick = {
                            createHome = !createHome
                            joinHome = false
                        }
                    ){
                        Text(text = "Crea tu hogar")
                    }

                }
                item{
                    Button(
                        onClick = {
                            joinHome = !joinHome
                            createHome = false
                        }
                    ){
                        Text(text = "Unete a un hogar")
                    }
                }

                if(createHome)
                {
                    item {
                        TextField(
                            value = homeName,
                            onValueChange = { homeName = it },
                            label = { Text(text = "Nombre del hogar") },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Button(
                            onClick = {
                                scope.launch {
                                    val home = HomeCreate(description = homeName)
                                    viewModelHome.createHome(home) { createdHome ->
                                        if (createdHome != null && user != null) {
                                            val homeUser = HomeUserCreate(
                                                homeId = createdHome.id,
                                                userId = user!!.id,
                                                userRole = 1
                                            )
                                            viewModelHU.createHomeUser(homeUser)

                                            Toast.makeText(context, "Hogar creado", Toast.LENGTH_SHORT).show()


                                        } else {
                                            Toast.makeText(context, "Error: Usuario u hogar no definido", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                                Toast.makeText(context, "Hogar creado", Toast.LENGTH_SHORT).show()
                                createHome = false
                            }
                        ) {
                            Text(text = "Crear")
                        }
                    }
                }

                if(joinHome)
                {
                    item {
                        TextField(
                            value = homeName,
                            onValueChange = { homeName = it },
                            label = { Text(text = "Nombre del hogar") },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Button(
                            onClick = {
                                /*
                                scope.launch {

                                }

                                 */
                                joinHome = false
                            }
                        ) {
                            Text(text = "Unirse")
                        }
                    }
                }
            }
        }

    }
}