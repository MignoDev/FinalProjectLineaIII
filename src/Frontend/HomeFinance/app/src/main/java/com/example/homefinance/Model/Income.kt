package com.example.homefinance.Model

import java.time.LocalDate

data class Income (
    val id: Long,
    val description: String,
    val amount: Double,
    val homeId: Long,
    val typeIncomeId: Long,
    val date: String
)

data class IncomeCreate (
    val description: String,
    val amount: Double,
    val homeId: Long,
    val typeIncomeId: Long,
    val date: String
)