package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.EarnInvestment
import com.example.homefinance.Model.HomeUser
import com.example.homefinance.Repository.HomeUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeUserViewModel : ViewModel() {

    //injección de repositorio
    private val repository = HomeUserRepository()

    //variable para gestionar los datos
    private val _homeUser = MutableLiveData<List<HomeUser>?>(emptyList())
    val homeUser: MutableLiveData<List<HomeUser>?> = _homeUser

    //obtener todos los datos
    fun listHomeUsers() {
        viewModelScope.launch {
            val homeUserList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _homeUser.postValue(homeUserList)
        }
    }

    //obtener dato por id
    fun findHomeUser(id: Long) {
        viewModelScope.launch {
            val homeUser = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createHomeUser(input: HomeUser) {
        viewModelScope.launch {
            repository.create(input)
            listHomeUsers()
        }
    }

    //actualizar registro
    fun updateHomeUser(id: Long, input: HomeUser) {
        viewModelScope.launch {
            repository.update(id, input)
            listHomeUsers()
        }
    }

    //eliminar registro por id
    fun deleteHomeUser(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listHomeUsers()
        }
    }
    
}