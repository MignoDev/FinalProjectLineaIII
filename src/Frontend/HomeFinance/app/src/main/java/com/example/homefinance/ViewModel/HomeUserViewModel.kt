package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.EarnInvestment
import com.example.homefinance.Model.HomeUser
import com.example.homefinance.Model.HomeUserCreate
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

    private val _homeUserUnique = MutableLiveData<HomeUser>(null)
    val homeUserUnique: MutableLiveData<HomeUser> = _homeUserUnique

    private val _homeUserCreate = MutableLiveData<HomeUser>(null)
    val homeUserCreate = MutableLiveData<HomeUser>(null)

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

    //Obtener dato por id del usuario
    fun findHomeUserByUserId(userId: Long) {
        viewModelScope.launch {
            val homeUser = withContext (Dispatchers.IO) {
                repository.findByUserID(userId)
            }
            _homeUserUnique.postValue(homeUser)
        }
    }

    //crear registro
    fun createHomeUser(input: HomeUserCreate) {
        viewModelScope.launch {
            repository.create(input)
        }
    }

    //actualizar registro
    fun updateHomeUser(id: Long, input: HomeUser) {
        viewModelScope.launch {
            repository.update(id, input)
        }
    }

    //eliminar registro por id
    fun deleteHomeUser(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }
    
}