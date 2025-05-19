package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private double amount;

    @Column(name = "home_id")
    private long homeId;

    @Column(name = "type_id")
    private long typeIncomeId;

    @ManyToOne
    @JoinColumn(name = "home_id", insertable = false, updatable = false)
    private Home home;

    @ManyToOne
    @JoinColumn(name = "type_id", updatable = false, insertable = false)
    private TypeIncome typeIncome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public long getTypeIncomeId() {
        return typeIncomeId;
    }

    public void setTypeIncomeId(long typeIncomeId) {
        this.typeIncomeId = typeIncomeId;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public TypeIncome getTypeIncome() {
        return typeIncome;
    }

    public void setTypeIncome(TypeIncome typeIncome) {
        this.typeIncome = typeIncome;
    }
}
