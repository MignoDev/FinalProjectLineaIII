package com.example.homefinance.Model

import java.time.LocalDate

data class PlannedExpenseDetail (
    val id: Long,
    val date: String,
    val plannedExpenseId: Long
)

data class PlannedExpenseDetailCreate (
    val date: String,
    val plannedExpenseId: Long
)