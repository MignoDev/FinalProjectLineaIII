package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.PlannedExpenseDetail
import com.example.homefinance.Model.PlannedExpenseDetailCreate

class PlannedExpenseDetailRepository {

    suspend fun list(): List<PlannedExpenseDetail> {
        return RetrofitClient.apiService.listPlannedExpenseDetail()
    }

    suspend fun find(id: Long): PlannedExpenseDetail {
        return RetrofitClient.apiService.findPlannedExpenseDetail(id)
    }

    suspend fun findByPlannedExpense (id: Long): PlannedExpenseDetail {
        return RetrofitClient.apiService.findPlannedExpenseDetailByPlannedExpenseId(id)
    }

    suspend fun create(input: PlannedExpenseDetailCreate) {
        return RetrofitClient.apiService.createPlannedExpenseDetail(input)
    }

    suspend fun update(id: Long, input: PlannedExpenseDetail) {
        return RetrofitClient.apiService.updatePlannedExpenseDetail(id, input)
    }

    suspend fun delete(id: Long): Long {
        return RetrofitClient.apiService.deletePlannedExpenseDetail(id)
    }

}