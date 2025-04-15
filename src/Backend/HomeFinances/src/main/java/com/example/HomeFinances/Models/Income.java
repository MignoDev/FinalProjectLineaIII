package com.example.HomeFinances.Models;

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

    private LocalDate date;

    private double amount;

    @Column(name = "home_id")
    private long home_id;

    @Column(name = "type_id")
    private long typeIncome_id;

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

    public long getHome_id() {
        return home_id;
    }

    public void setHome_id(long home_id) {
        this.home_id = home_id;
    }

    public long getTypeIncome_id() {
        return typeIncome_id;
    }

    public void setTypeIncome_id(long typeIncome_id) {
        this.typeIncome_id = typeIncome_id;
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
