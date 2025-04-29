package com.example.homefinance.Model

data class Income (
    val id: Long,
    val description: String,
    val amount: Double,
    val homeId: Long,
    val typeIncomeId: Long
)