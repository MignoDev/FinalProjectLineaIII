package com.example.homefinance.Repository

import android.util.Log
import com.example.homefinance.Interfaces.RetrofitClient
import com.example.homefinance.Model.Home
import com.example.homefinance.Model.HomeCreate

class HomeRepository {

    suspend fun list(): List<Home> {
        return RetrofitClient.apiService.listHome()
    }

    suspend fun find(id: Long): Home? {
        val response = RetrofitClient.apiService.findHome(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            // Puedes registrar el error o lanzar una excepción más clara
            Log.e("API", "Error: ${response.code()} - ${response.errorBody()?.string()}")
            null
        }
    }

    suspend fun create(input: HomeCreate) : Home {
        return RetrofitClient.apiService.createHome(input)
    }

    suspend fun update(id: Long, input: Home) {
        return RetrofitClient.apiService.updateHome(id, input)
    }

    suspend fun delete(id: Long) {
        return RetrofitClient.apiService.deleteHome(id)
    }

}