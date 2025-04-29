package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.TypeExpense
import com.example.homefinance.Repository.TypeExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeExpenseViewModel : ViewModel() {

    //injección de repositorio
    private val repository = TypeExpenseRepository()

    //variable para gestionar los datos
    private val _typeExpense = MutableLiveData<List<TypeExpense>?>(emptyList())
    val typeExpense: MutableLiveData<List<TypeExpense>?> = _typeExpense

    //obtener todos los datos
    fun listTypeExpenses() {
        viewModelScope.launch {
            val typeExpenseList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _typeExpense.postValue(typeExpenseList)
        }
    }

    //obtener dato por id
    fun findTypeExpense(id: Long) {
        viewModelScope.launch {
            val typeExpense = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createTypeExpense(input: TypeExpense) {
        viewModelScope.launch {
            repository.create(input)
            listTypeExpenses()
        }
    }

    //actualizar registro
    fun updateTypeExpense(id: Long, input: TypeExpense) {
        viewModelScope.launch {
            repository.update(id, input)
            listTypeExpenses()
        }
    }

    //eliminar registro por id
    fun deleteTypeExpense(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listTypeExpenses()
        }
    }
    
}