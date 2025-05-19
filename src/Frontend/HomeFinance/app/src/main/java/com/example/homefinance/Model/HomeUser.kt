package com.example.homefinance.Model

data class HomeUser (
    val id: Long,
    val homeId: Long,
    val userId: Long,
    val userRole: Int,
)

data class HomeUserCreate (
    val homeId: Long,
    val userId: Long,
    val userRole: Int,
)