package com.example.homefinance.Repository

import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.LoginRequest
import com.example.homefinance.Model.User
import com.example.homefinance.Model.UserRequest

class UserRepository {

    suspend fun list(): List<User> {
        return RetrofitClient.apiService.listUser()
    }

    suspend fun find(id: Long): User {
        return RetrofitClient.apiService.findUser(id)
    }

    suspend fun findUserName(userName: String): User {
        return RetrofitClient.apiService.findUserName(userName)
    }

    suspend fun create(input: UserRequest) {
        return RetrofitClient.apiService.createUser(input)
    }

    suspend fun login(input: LoginRequest): Boolean {
        return RetrofitClient.apiService.login(input)
    }

    suspend fun update(id: Long, input: UserRequest) {
        return RetrofitClient.apiService.updateUser(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteUser(id)
    }

}