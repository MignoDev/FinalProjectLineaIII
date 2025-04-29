package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.User

class UserRepository {

    suspend fun list(): List<User> {
        return RetrofitClient.apiService.listUser()
    }

    suspend fun find(id: Long): User {
        return RetrofitClient.apiService.findUser(id)
    }

    suspend fun create(input: User) {
        return RetrofitClient.apiService.createUser(input)
    }

    suspend fun update(id: Long, input: User) {
        return RetrofitClient.apiService.updateUser(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteUser(id)
    }

}