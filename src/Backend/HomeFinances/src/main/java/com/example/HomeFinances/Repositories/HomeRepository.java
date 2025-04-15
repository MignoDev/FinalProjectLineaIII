package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.Home;
import com.example.HomeFinances.Models.HomeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
}
