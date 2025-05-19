package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.PlannedExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannedExpenseDetailRepository extends JpaRepository<PlannedExpenseDetail, Long> {

    @Query(value = """
            select * from planned_expense_detail where planned_expense_id = :id
            """, nativeQuery = true)
    PlannedExpenseDetail findbyPlannedExpenseId(@Param("id") long id);
}
