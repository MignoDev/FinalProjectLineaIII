package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.Income
import com.example.homefinance.Model.IncomeCreate
import com.example.homefinance.Repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IncomeViewModel : ViewModel() {

    //injección de repositorio
    private val repository = IncomeRepository()

    //variable para gestionar los datos
    private val _income = MutableLiveData<List<Income>?>(emptyList())
    val income: MutableLiveData<List<Income>?> = _income

    //obtener todos los datos
    fun listIncomes() {
        viewModelScope.launch {
            val incomeList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _income.postValue(incomeList)
        }
    }

    //obtener dato por id
    fun findIncome(id: Long) {
        viewModelScope.launch {
            val income = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    fun findIncomeByHomeId(id: Long) {
        viewModelScope.launch {
            val incomeList = withContext(Dispatchers.IO) {
                repository.findByHomeId(id)
            }
            _income.postValue(incomeList)
        }
    }

    //crear registro
    fun createIncome(input: IncomeCreate) {
        viewModelScope.launch {
            repository.create(input)
        }
    }

    //actualizar registro
    fun updateIncome(id: Long, input: Income) {
        viewModelScope.launch {
            repository.update(id, input)
            listIncomes()
        }
    }

    //eliminar registro por id
    fun deleteIncome(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listIncomes()
        }
    }
    
}