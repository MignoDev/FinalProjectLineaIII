package com.example.homefinance.Model

data class Home (
    val id: Long,
    val description: String
)

data class HomeCreate (
    val description: String
)