package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.HomeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeUserRepository extends JpaRepository<HomeUser, Long> {
}
