package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.ActualExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualExpenseRepository extends JpaRepository<ActualExpense, Long> {

}
