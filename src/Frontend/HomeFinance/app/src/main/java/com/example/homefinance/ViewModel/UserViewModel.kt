package com.example.homefinance.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinance.Model.LoginRequest
import com.example.homefinance.Model.User
import com.example.homefinance.Model.UserRequest
import com.example.homefinance.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

    //injección de repositorio
    private val repository = UserRepository()

    //variable para gestionar los datos
    private val _user = MutableLiveData<List<User>?>(emptyList())
    private val _logedIn = MutableLiveData<Boolean>(false)
    private val _userUnique = MutableLiveData<User>(null)
    private val _userInvite = MutableLiveData<User>(null)

    val user: LiveData<List<User>?> = _user
    val loggedIn: LiveData<Boolean> = _logedIn
    val userUnique: LiveData<User> = _userUnique
    val userInvite: LiveData<User> = _userInvite


    //obtener todos los datos
    fun listUsers() {
        viewModelScope.launch {
            val userList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _user.postValue(userList)
        }
    }

    //obtener dato por id
    fun findUser(id: Long) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                repository.find(id)
            }
            _userUnique.postValue(user)
        }
    }

    //obetener registro por nombre de usuario
    fun findUserName(userName: String) {
        viewModelScope.launch {
            try {
                val user = withContext (Dispatchers.IO) {
                    repository.findUserName(userName)
                }
                _userInvite.postValue(user)
            } catch (e: Exception)
            {

            }

        }
    }

    fun loginFindUer (userName: String) {
        viewModelScope.launch {
            val user = withContext (Dispatchers.IO) {
                repository.findUserName(userName)
            }
            _userUnique.postValue(user)
        }
    }

    //crear registro
    fun createUser(input: UserRequest) {
        viewModelScope.launch {
            repository.create(input)
            listUsers()
        }
    }

    //inciar sesión
    fun login(input: LoginRequest) {
        viewModelScope.launch {
            val loggedIn = withContext(Dispatchers.IO) {
                repository.login(input)
            }
            _logedIn.postValue(loggedIn)
        }
    }

    //actualizar registro
    fun updateUser(id: Long, input: UserRequest) {
        viewModelScope.launch {
            repository.update(id, input)
            listUsers()
        }
    }

    //eliminar registro por id
    fun deleteUser(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listUsers()
        }
    }
    
}