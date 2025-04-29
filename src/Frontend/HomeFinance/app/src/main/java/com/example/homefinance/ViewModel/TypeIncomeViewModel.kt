package com.example.homefinance.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinance.Model.TypeIncome
import com.example.homefinance.Repository.TypeIncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeIncomeViewModel : ViewModel() {

    //injección de repositorio
    private val repository = TypeIncomeRepository()

    //variable para gestionar los datos
    private val _typeIncome = MutableLiveData<List<TypeIncome>?>(emptyList())
    val typeIncome: MutableLiveData<List<TypeIncome>?> = _typeIncome

    //obtener todos los datos
    fun listTypeIncomes() {
        viewModelScope.launch {
            val typeIncomeList = withContext(Dispatchers.IO) {
                repository.list()
            }
            _typeIncome.postValue(typeIncomeList)
        }
    }

    //obtener dato por id
    fun findTypeIncome(id: Long) {
        viewModelScope.launch {
            val typeIncome = withContext(Dispatchers.IO) {
                repository.find(id)
            }
        }
    }

    //crear registro
    fun createTypeIncome(input: TypeIncome) {
        viewModelScope.launch {
            repository.create(input)
            listTypeIncomes()
        }
    }

    //actualizar registro
    fun updateTypeIncome(id: Long, input: TypeIncome) {
        viewModelScope.launch {
            repository.update(id, input)
            listTypeIncomes()
        }
    }

    //eliminar registro por id
    fun deleteTypeIncome(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            listTypeIncomes()
        }
    }
    
}