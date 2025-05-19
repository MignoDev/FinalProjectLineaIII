package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.Home
import com.example.homefinance.Model.HomeCreate
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

    private val _homeUnique = MutableLiveData<Home?>(null)
    val homeUnique: MutableLiveData<Home?> = _homeUnique

    private val _homeId = MutableLiveData<Home>(null)
    val homeId: MutableLiveData<Home> = _homeId

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
            _homeUnique.postValue(home)
        }
    }

    //crear registro
    /*
    fun createHome(input: HomeCreate) {
        viewModelScope.launch {
            val homeId = withContext(Dispatchers.IO) {
                repository.create(input)
            }
            _homeId.postValue(homeId)
        }
    }
*/
    fun createHome(home: HomeCreate, onResult: (Home?) -> Unit) {
        viewModelScope.launch {
            try {
                // Llama al repositorio o caso de uso que cree el hogar
                val newHome = repository.create(home) // Devuelve un objeto Home o null
                onResult(newHome)

            } catch (e: Exception) {
                onResult(null) // En caso de error, devolver null
            }
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