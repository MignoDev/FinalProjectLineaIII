package com.example.homefinance.Model

import java.time.LocalDate

data class PlannedExpenseDetail (
    val id: Long,
    val date: LocalDate,
    val plannedExpenseId: Long
)