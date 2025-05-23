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
    List<PlannedExpense> findByHomeId(@Param("id") long id);

    @Query(value = """
            SELECT pe.id, pe.amount, pe.comment, pe.description, pe.home_id, pe.type_id, ped.id as detail_id, ped.date, ped.planned_expense_id
            FROM planned_expense pe JOIN planned_expense_detail ped ON pe.id = ped.planned_expense_id WHERE pe.home_id = :id
            """, nativeQuery = true)
    List<Object[]> findFullExpenses(@Param("id") Long homeId); // esto retorna datos "crudos"
}
