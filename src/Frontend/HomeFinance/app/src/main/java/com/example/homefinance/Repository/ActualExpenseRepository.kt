package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.ActualExpense

class ActualExpenseRepository {
    suspend fun list(): List<ActualExpense> {
        return RetrofitClient.apiService.listActualExpense()
    }

    suspend fun find(id: Long): ActualExpense {
        return RetrofitClient.apiService.findActualExpense(id)
    }

    suspend fun create(input: ActualExpense) {
        return RetrofitClient.apiService.createActualExpense(input)
    }

    suspend fun update(id: Long, input: ActualExpense) {
        return RetrofitClient.apiService.updateActualExpense(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteActualExpense(id)
    }
}