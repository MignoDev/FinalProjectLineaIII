package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.EarnInvestment

class EarnInvestmentRepository {

    suspend fun list(): List<EarnInvestment> {
        return RetrofitClient.apiService.listEarnInvestment()
    }

    suspend fun find(id: Long): EarnInvestment{
        return RetrofitClient.apiService.findEarnInvestment(id)
    }

    suspend fun create(input: EarnInvestment) {
        return RetrofitClient.apiService.createEarnInvestment(input)
    }

    suspend fun update(id: Long, input: EarnInvestment) {
        return RetrofitClient.apiService.updateEarnInvestment(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteEarnInvestment(id)
    }

}