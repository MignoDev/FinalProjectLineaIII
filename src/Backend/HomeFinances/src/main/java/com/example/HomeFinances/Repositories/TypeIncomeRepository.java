package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.TypeIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeIncomeRepository extends JpaRepository<TypeIncome, Long> {
}
