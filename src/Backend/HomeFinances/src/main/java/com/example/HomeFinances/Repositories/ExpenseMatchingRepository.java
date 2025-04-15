package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.ExpenseMatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseMatchingRepository extends JpaRepository<ExpenseMatching, Long> {
}
