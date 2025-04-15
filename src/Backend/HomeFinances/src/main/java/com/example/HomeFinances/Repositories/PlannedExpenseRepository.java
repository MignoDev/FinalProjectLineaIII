package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.PlannedExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannedExpenseRepository extends JpaRepository<PlannedExpense, Long> {
}
