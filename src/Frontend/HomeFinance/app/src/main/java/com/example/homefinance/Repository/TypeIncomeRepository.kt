package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.TypeIncome

class TypeIncomeRepository {

    suspend fun list(): List<TypeIncome> {
        return RetrofitClient.apiService.listTypeIncome()
    }

    suspend fun find(id: Long): TypeIncome {
        return RetrofitClient.apiService.findTypeIncome(id)
    }

    suspend fun create(input: TypeIncome) {
        return RetrofitClient.apiService.createTypeIncome(input)
    }

    suspend fun update(id: Long, input: TypeIncome) {
        return RetrofitClient.apiService.updateTypeIncome(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteTypeIncome(id)
    }

}