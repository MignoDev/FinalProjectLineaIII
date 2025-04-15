package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.PlannedExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannedExpenseDetailRepository extends JpaRepository<PlannedExpenseDetail, Long> {
}
