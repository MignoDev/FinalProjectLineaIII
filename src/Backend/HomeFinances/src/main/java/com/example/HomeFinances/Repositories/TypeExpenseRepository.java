package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.TypeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeExpenseRepository extends JpaRepository<TypeExpense, Long> {
}
