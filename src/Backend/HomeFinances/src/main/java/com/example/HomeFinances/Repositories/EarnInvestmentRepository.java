package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.EarnInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarnInvestmentRepository extends JpaRepository<EarnInvestment, Long> {

}
