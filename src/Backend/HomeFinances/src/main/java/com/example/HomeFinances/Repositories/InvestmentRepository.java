package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
