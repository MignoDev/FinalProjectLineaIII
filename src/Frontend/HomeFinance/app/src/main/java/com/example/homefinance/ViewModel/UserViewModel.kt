package com.example.homefinance.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val user: LiveData<List<User>?> = _user

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
        }
    }

    //crear registro
    fun createUser(input: UserRequest) {
        viewModelScope.launch {
            repository.create(input)
            listUsers()
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