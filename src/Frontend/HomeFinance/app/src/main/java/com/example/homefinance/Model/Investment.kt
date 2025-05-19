package com.example.homefinance.Model

import java.time.LocalDate

data class Investment (
    val id: Long,
    val description: String,
    val date: String,
    val amount: Double,
    val homeId: Long
)

data class InvestmentCreate (
    val description: String,
    val date: String,
    val amount: Double,
    val homeId: Long
)