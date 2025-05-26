package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.PlannedExpenseDetail
import com.example.homefinance.Model.PlannedExpenseDetailCreate
import com.example.homefinance.Repository.PlannedExpenseDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlannedExpenseDetailViewModel : ViewModel() {

    //injección de repositorio
    private val repository = PlannedExpenseDetailRepository()

    //variable para gestionar los datos
    private val _plannedExpenseDetail = MutableLiveData<List<PlannedExpenseDetail>?>(emptyList())
    private val _deleted = MutableLiveData<Long>(null)


    val plannedExpenseDetail: MutableLiveData<List<PlannedExpenseDetail>?> = _plannedExpenseDetail
    val deleted: MutableLiveData<Long> = _deleted

    //obtener todos los datos
    fun listPlannedExpenseDetails() {
        viewModelScope.launch {
            val plannedExpenseDetailList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _plannedExpenseDetail.postValue(plannedExpenseDetailList)
        }
    }

    //obtener dato por id
    fun findPlannedExpenseDetail(id: Long) {
        viewModelScope.launch {
            val plannedExpenseDetail = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    fun findPlannedExpenseDetailByPlannedExpenseId(id: Long) {
        viewModelScope.launch {
            val plannedExpenseDetailList = withContext(Dispatchers.IO) {
                repository.findByPlannedExpense(id)
            }
        }
    }

    //crear registro
    fun createPlannedExpenseDetail(input: PlannedExpenseDetailCreate) {
        viewModelScope.launch {
            repository.create(input)
        }
    }

    //actualizar registro
    fun updatePlannedExpenseDetail(id: Long, input: PlannedExpenseDetail) {
        viewModelScope.launch {
            repository.update(id, input)
            listPlannedExpenseDetails()
        }
    }

    //eliminar registro por id
    fun deletePlannedExpenseDetail(id: Long) {
        viewModelScope.launch {
            val response = repository.delete(id)
            _deleted.postValue(response)
            listPlannedExpenseDetails()
        }

    }
    
}