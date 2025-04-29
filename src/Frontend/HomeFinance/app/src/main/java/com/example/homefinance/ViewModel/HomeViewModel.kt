package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.Home
import com.example.homefinance.Repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    //injección de repositorio
    private val repository = HomeRepository()

    //variable para gestionar los datos
    private val _home = MutableLiveData<List<Home>?>(emptyList())
    val home: MutableLiveData<List<Home>?> = _home

    //obtener todos los datos
    fun listHomes() {
        viewModelScope.launch {
            val homeList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _home.postValue(homeList)
        }
    }

    //obtener dato por id
    fun findHome(id: Long) {
        viewModelScope.launch {
            val home = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createHome(input: Home) {
        viewModelScope.launch {
            repository.create(input)
            listHomes()
        }
    }

    //actualizar registro
    fun updateHome(id: Long, input: Home) {
        viewModelScope.launch {
            repository.update(id, input)
            listHomes()
        }
    }

    //eliminar registro por id
    fun deleteHome(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listHomes()
        }
    }

}