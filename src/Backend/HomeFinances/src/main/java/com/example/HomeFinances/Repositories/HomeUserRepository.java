package com.example.HomeFinances.Repositories;

import com.example.HomeFinances.Models.HomeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeUserRepository extends JpaRepository<HomeUser, Long> {

    @Query(value = """
            select * from home_user hu where user_id = :user
            """, nativeQuery = true)
    HomeUser findByUserId (@Param("user") long user);

}
