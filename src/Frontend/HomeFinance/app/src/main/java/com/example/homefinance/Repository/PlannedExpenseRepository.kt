package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.PlannedExpense
import com.example.homefinance.Model.PlannedExpenseCreate

class PlannedExpenseRepository {

    suspend fun list(): List<PlannedExpense> {
        return RetrofitClient.apiService.listPlannedExpense()
    }

    suspend fun find(id: Long): PlannedExpense {
        return RetrofitClient.apiService.findPlannedExpense(id)
    }

    suspend fun create(input: PlannedExpenseCreate): PlannedExpense {
        return RetrofitClient.apiService.createPlannedExpense(input)
    }

    suspend fun update(id: Long, input: PlannedExpense) {
        return RetrofitClient.apiService.updatePlannedExpense(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deletePlannedExpense(id)
    }

}