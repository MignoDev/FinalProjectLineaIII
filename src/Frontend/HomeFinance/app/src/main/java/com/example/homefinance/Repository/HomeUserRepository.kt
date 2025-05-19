package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.HomeUser
import com.example.homefinance.Model.HomeUserCreate

class HomeUserRepository {

    suspend fun list(): List<HomeUser> {
        return RetrofitClient.apiService.listHomeUser()
    }

    suspend fun find(id: Long): HomeUser {
        return RetrofitClient.apiService.findHomeUser(id)
    }

    suspend fun findByUserID(userId: Long): HomeUser {
        return RetrofitClient.apiService.findHomeUserByUserId(userId)
    }

    suspend fun create(input: HomeUserCreate) {
        return RetrofitClient.apiService.createHomeUser(input)
    }

    suspend fun update(id: Long, input: HomeUser) {
        return RetrofitClient.apiService.updateHomeUser(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteHomeUser(id)
    }

}