package com.example.homefinance.Model

data class User(
    val id: Long,
    val userName: String,
    val password: String,
    val nickName: String
)

data class UserRequest(
    val userName: String,
    val password: String,
    val nickName: String
)