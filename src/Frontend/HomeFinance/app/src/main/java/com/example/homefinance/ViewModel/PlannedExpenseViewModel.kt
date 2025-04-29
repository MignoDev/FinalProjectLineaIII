package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.PlannedExpense
import com.example.homefinance.Repository.PlannedExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlannedExpenseViewModel : ViewModel() {

    //injección de repositorio
    private val repository = PlannedExpenseRepository()

    //variable para gestionar los datos
    private val _plannedExpense = MutableLiveData<List<PlannedExpense>?>(emptyList())
    val plannedExpense: MutableLiveData<List<PlannedExpense>?> = _plannedExpense

    //obtener todos los datos
    fun listPlannedExpenses() {
        viewModelScope.launch {
            val plannedExpenseList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _plannedExpense.postValue(plannedExpenseList)
        }
    }

    //obtener dato por id
    fun findPlannedExpense(id: Long) {
        viewModelScope.launch {
            val plannedExpense = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createPlannedExpense(input: PlannedExpense) {
        viewModelScope.launch {
            repository.create(input)
            listPlannedExpenses()
        }
    }

    //actualizar registro
    fun updatePlannedExpense(id: Long, input: PlannedExpense) {
        viewModelScope.launch {
            repository.update(id, input)
            listPlannedExpenses()
        }
    }

    //eliminar registro por id
    fun deletePlannedExpense(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listPlannedExpenses()
        }
    }
    
}