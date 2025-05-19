package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query(value = """
            select * from income where home_id = :id
            """, nativeQuery = true)
    List<Income> findByHomeId(@Param("id") long id);
}
