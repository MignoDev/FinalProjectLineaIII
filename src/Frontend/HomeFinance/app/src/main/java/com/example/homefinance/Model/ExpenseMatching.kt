package com.example.homefinance.Model

data class ExpenseMatching (
    val id: Long,
    val actualExpenseId: Long,
    val plannedExpenseDetailId: Long,
)