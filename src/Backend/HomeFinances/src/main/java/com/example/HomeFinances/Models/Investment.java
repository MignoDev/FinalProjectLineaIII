package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private LocalDate date;

    private double amount;

    @Column(name = "home_id")
    private long homeId;

    @ManyToOne
    @JoinColumn(name = "home_id", insertable = false, updatable = false)
    @JsonIgnore
    private Home home;

    @OneToMany(mappedBy = "investment")
    @JsonIgnore
    private List<EarnInvestment> earnInvestments;

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

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public List<EarnInvestment> getEarnInvestments() {
        return earnInvestments;
    }

    public void setEarnInvestments(List<EarnInvestment> earnInvestments) {
        this.earnInvestments = earnInvestments;
    }
}
