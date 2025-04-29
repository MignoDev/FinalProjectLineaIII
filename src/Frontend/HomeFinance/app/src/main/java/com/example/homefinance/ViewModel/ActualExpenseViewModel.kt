package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.ActualExpense
import com.example.homefinance.Repository.ActualExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActualExpenseViewModel : ViewModel() {

    //injección de repositorio
    private val repository = ActualExpenseRepository()

    //variable para gestionar los datos
    private val _actualExpenses = MutableLiveData<List<ActualExpense>?>(emptyList())
    val actualExpense: MutableLiveData<List<ActualExpense>?> = _actualExpenses

    //obtener todos los gastos reales
    fun listActualExpenses() {
        viewModelScope.launch {
            val actualExpenseList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _actualExpenses.postValue(actualExpenseList)
        }
    }

    //obtener gastos reales por id
    fun findActualExpense(id: Long) {
        viewModelScope.launch {
            val actualExpense = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear gasto real
    fun createActualExpense(input: ActualExpense) {
        viewModelScope.launch {
            repository.create(input)
            listActualExpenses()
        }
    }

    //actualizar gasto real
    fun updateActualExpense(id: Long, input: ActualExpense) {
        viewModelScope.launch {
            repository.update(id, input)
            listActualExpenses()
        }
    }

    //eliminar gasto real por id
    fun deleteActualExpense(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listActualExpenses()
        }
    }

}