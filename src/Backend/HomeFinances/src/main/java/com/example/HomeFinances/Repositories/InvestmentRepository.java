package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    @Query(value = """
            select * from investment where home_id = :id
            """, nativeQuery = true)
    List<Investment> FindByHomeId(@Param("id") long id);
}
