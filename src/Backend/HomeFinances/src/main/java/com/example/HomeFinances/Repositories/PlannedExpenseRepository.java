package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.PlannedExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PlannedExpenseRepository extends JpaRepository<PlannedExpense, Long> {

    @Query(value = """
            select * from planned_expense where home_id = :id
            """, nativeQuery = true)
    public List<PlannedExpense> findByHomeId(@Param("id") long id);
}
