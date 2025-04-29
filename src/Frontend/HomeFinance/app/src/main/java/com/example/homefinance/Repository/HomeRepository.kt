package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.Home

class HomeRepository {

    suspend fun list(): List<Home> {
        return RetrofitClient.apiService.listHome()
    }

    suspend fun find(id: Long): Home {
        return RetrofitClient.apiService.findHome(id)
    }

    suspend fun create(input: Home) {
        return RetrofitClient.apiService.createHome(input)
    }

    suspend fun update(id: Long, input: Home) {
        return RetrofitClient.apiService.updateHome(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteHome(id)
    }

}