package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class TypeExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private String icon;

    @OneToMany(mappedBy = "typeExpense")
    @JsonIgnore
    private List<ActualExpense> actualExpenses;

    @OneToMany(mappedBy = "typeExpense")
    @JsonIgnore
    private List<PlannedExpense> plannedExpenses;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ActualExpense> getActualExpenses() {
        return actualExpenses;
    }

    public void setActualExpenses(List<ActualExpense> actualExpenses) {
        this.actualExpenses = actualExpenses;
    }

    public List<PlannedExpense> getPlannedExpenses() {
        return plannedExpenses;
    }

    public void setPlannedExpenses(List<PlannedExpense> plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }
}
