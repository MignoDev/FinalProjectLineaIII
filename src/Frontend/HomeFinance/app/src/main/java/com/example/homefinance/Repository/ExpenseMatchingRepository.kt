package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.ExpenseMatching

class ExpenseMatchingRepository {

    suspend fun list(): List<ExpenseMatching> {
        return RetrofitClient.apiService.listExpenseMatching()
    }

    suspend fun find(id: Long): ExpenseMatching {
        return RetrofitClient.apiService.findExpenseMatching(id)
    }

    suspend fun create(input: ExpenseMatching) {
        return RetrofitClient.apiService.createExpenseMatching(input)
    }

    suspend fun update(id: Long, input: ExpenseMatching) {
        return RetrofitClient.apiService.updateExpenseMatching(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteExpenseMatching(id)
    }

}