package com.example.homefinance.Model

data class ActualExpense (
    val id: Long,
    val description: String,
    val amount: Double,
    val typeId: Long,
    val homeId: Long
)