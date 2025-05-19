package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.Income
import com.example.homefinance.Model.IncomeCreate

class IncomeRepository {

    suspend fun list(): List<Income> {
        return RetrofitClient.apiService.listIncome()
    }

    suspend fun find(id: Long): Income {
        return RetrofitClient.apiService.findIncome(id)
    }

    suspend fun findByHomeId(id: Long): List<Income> {
        return RetrofitClient.apiService.findIncomeByHomeId(id)
    }

    suspend fun create(input: IncomeCreate) {
        return RetrofitClient.apiService.createIncome(input)
    }

    suspend fun update(id: Long, input: Income) {
        return RetrofitClient.apiService.updateIncome(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteIncome(id)
    }

}