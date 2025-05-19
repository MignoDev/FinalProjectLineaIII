package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.Investment
import com.example.homefinance.Model.InvestmentCreate
import com.example.homefinance.Repository.InvestmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InvestmentViewModel : ViewModel() {

    //injección de repositorio
    private val repository = InvestmentRepository()

    //variable para gestionar los datos
    private val _investment = MutableLiveData<List<Investment>?>(emptyList())
    val investment: MutableLiveData<List<Investment>?> = _investment

    //obtener todos los datos
    fun listInvestments() {
        viewModelScope.launch {
            val investmentList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _investment.postValue(investmentList)
        }
    }

    //obtener dato por id
    fun findInvestment(id: Long) {
        viewModelScope.launch {
            val investment = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    fun findInvestmentByHomeId(id: Long) {
        viewModelScope.launch {
            val investmentList = withContext(Dispatchers.IO) {
                repository.findByHomeId(id)
            }
            _investment.postValue(investmentList)
        }
    }

    //crear registro
    fun createInvestment(input: InvestmentCreate) {
        viewModelScope.launch {
            repository.create(input)
            listInvestments()
        }
    }

    //actualizar registro
    fun updateInvestment(id: Long, input: Investment) {
        viewModelScope.launch {
            repository.update(id, input)
            listInvestments()
        }
    }

    //eliminar registro por id
    fun deleteInvestment(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listInvestments()
        }
    }

}