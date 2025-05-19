package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.Investment
import com.example.homefinance.Model.InvestmentCreate

class InvestmentRepository {

    suspend fun list(): List<Investment> {
        return RetrofitClient.apiService.listInvestment()
    }

    suspend fun find(id: Long): Investment {
        return RetrofitClient.apiService.findInvestment(id)
    }

    suspend fun create(input: InvestmentCreate) {
        return RetrofitClient.apiService.createInvestment(input)
    }

    suspend fun update(id: Long, input: Investment) {
        return RetrofitClient.apiService.updateInvestment(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteInvestment(id)
    }

}