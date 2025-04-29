package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.ExpenseMatching
import com.example.homefinance.Repository.ExpenseMatchingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpenseMatchingViewModel : ViewModel() {

    //injección de repositorio
    private val repository = ExpenseMatchingRepository()

    //variable para gestionar los datos
    private val _expenseMatching = MutableLiveData<List<ExpenseMatching>?>(emptyList())
    val expenseMatching: MutableLiveData<List<ExpenseMatching>?> = _expenseMatching

    //obtener todos los datos
    fun listExpenseMatchings() {
        viewModelScope.launch {
            val expenseMatchingList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _expenseMatching.postValue(expenseMatchingList)
        }
    }

    //obtener dato por id
    fun findExpenseMatching(id: Long) {
        viewModelScope.launch {
            val expenseMatching = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createExpenseMatching(input: ExpenseMatching) {
        viewModelScope.launch {
            repository.create(input)
            listExpenseMatchings()
        }
    }

    //actualizar registro
    fun updateExpenseMatching(id: Long, input: ExpenseMatching) {
        viewModelScope.launch {
            repository.update(id, input)
            listExpenseMatchings()
        }
    }

    //eliminar registro por id
    fun deleteExpenseMatching(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listExpenseMatchings()
        }
    }

}