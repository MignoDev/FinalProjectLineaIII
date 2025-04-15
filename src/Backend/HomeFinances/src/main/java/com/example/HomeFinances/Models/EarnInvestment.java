package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EarnInvestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double amount;

    private LocalDate date;

    @Column(name = "investment_id")
    private long investmentId;

    @ManyToOne
    @JoinColumn(name = "investment_id", updatable = false, insertable = false)
    private Investment investment;
}
