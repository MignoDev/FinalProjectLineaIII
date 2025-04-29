package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.EarnInvestment
import com.example.homefinance.Repository.EarnInvestmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EarnInvestmentViewModel : ViewModel() {

    //injección de repositorio
    private val repository = EarnInvestmentRepository()

    //variable para gestionar los datos
    private val _earnInvestment = MutableLiveData<List<EarnInvestment>?>(emptyList())
    val earnInvestment: MutableLiveData<List<EarnInvestment>?> = _earnInvestment

    //obtener todos los datos
    fun listEarnInvestments() {
        viewModelScope.launch {
            val earnInvestmentList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _earnInvestment.postValue(earnInvestmentList)
        }
    }

    //obtener dato por id
    fun findEarnInvestment(id: Long) {
        viewModelScope.launch {
            val earnInvestment = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createEarnInvestment(input: EarnInvestment) {
        viewModelScope.launch {
            repository.create(input)
            listEarnInvestments()
        }
    }

    //actualizar registro
    fun updateEarnInvestment(id: Long, input: EarnInvestment) {
        viewModelScope.launch {
            repository.update(id, input)
            listEarnInvestments()
        }
    }

    //eliminar registro por id
    fun deleteEarnInvestment(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listEarnInvestments()
        }
    }

}