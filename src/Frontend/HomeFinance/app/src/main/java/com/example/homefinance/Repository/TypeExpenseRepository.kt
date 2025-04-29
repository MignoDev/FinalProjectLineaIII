package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.TypeExpense

class TypeExpenseRepository {

    suspend fun list(): List<TypeExpense> {
        return RetrofitClient.apiService.listTypeExpense()
    }

    suspend fun find(id: Long): TypeExpense {
        return RetrofitClient.apiService.findTypeExpense(id)
    }

    suspend fun create(input: TypeExpense) {
        return RetrofitClient.apiService.createTypeExpense(input)
    }

    suspend fun update(id: Long, input: TypeExpense) {
        return RetrofitClient.apiService.updateTypeExpense(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteTypeExpense(id)
    }

}