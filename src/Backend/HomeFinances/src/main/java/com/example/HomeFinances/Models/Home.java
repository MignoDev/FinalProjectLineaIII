package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<Investment> investments;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<PlannedExpense> plannedExpenses;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<ActualExpense> actualExpenses;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<Income> incomes;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<HomeUser> homeUsers;

    public List<HomeUser> getHomeUsers() {
        return homeUsers;
    }

    public void setHomeUsers(List<HomeUser> homeUsers) {
        this.homeUsers = homeUsers;
    }

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

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<PlannedExpense> getPlannedExpenses() {
        return plannedExpenses;
    }

    public void setPlannedExpenses(List<PlannedExpense> plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public List<ActualExpense> getActualExpenses() {
        return actualExpenses;
    }

    public void setActualExpenses(List<ActualExpense> actualExpenses) {
        this.actualExpenses = actualExpenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
}
