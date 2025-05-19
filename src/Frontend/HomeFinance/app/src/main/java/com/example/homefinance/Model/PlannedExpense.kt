package com.example.homefinance.Model

data class PlannedExpense (
    val id: Long,
    val description: String,
    val amount: Double,
    val comment: String,
    val typeId: Long,
    val homeId: Long
)

data class PlannedExpenseCreate (
    val description: String,
    val amount: Double,
    val comment: String,
    val typeId: Long,
    val homeId: Long
)