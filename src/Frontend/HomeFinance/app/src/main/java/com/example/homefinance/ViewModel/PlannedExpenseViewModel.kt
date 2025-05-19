package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.PlannedExpense
import com.example.homefinance.Model.PlannedExpenseCreate
import com.example.homefinance.Repository.PlannedExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlannedExpenseViewModel : ViewModel() {

    //injección de repositorio
    private val repository = PlannedExpenseRepository()

    //variable para gestionar los datos
    private val _plannedExpense = MutableLiveData<List<PlannedExpense>?>(emptyList())
    private val _plannedExpenseCreate = MutableLiveData<PlannedExpense>()

    val plannedExpense: MutableLiveData<List<PlannedExpense>?> = _plannedExpense
    val plannedExpenseCreate: MutableLiveData<PlannedExpense> = _plannedExpenseCreate

    //obtener todos los datos
    fun listPlannedExpenses() {
        viewModelScope.launch {
            val plannedExpenseList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _plannedExpense.postValue(plannedExpenseList)
        }
    }

    //Obtener filas por home_id
    fun findPlannedExpenseByHomeId(id: Long) {
        viewModelScope.launch {
            val plannedExpenseList = withContext(Dispatchers.IO) {
                repository.findByHome(id)
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
    fun createPlannedExpense(input: PlannedExpenseCreate) {
        viewModelScope.launch {
            val plannedExpenseCreate = withContext (Dispatchers.IO) {
                repository.create(input)
            }
            _plannedExpenseCreate.postValue(plannedExpenseCreate)
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