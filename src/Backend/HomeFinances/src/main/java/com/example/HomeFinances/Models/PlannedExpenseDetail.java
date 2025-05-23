package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class PlannedExpenseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @Column(name = "planned_expense_id")
    private long plannedExpenseId;

    @ManyToOne
    @JoinColumn(name = "planned_expense_id", insertable = false, updatable = false)
    @JsonIgnore
    private PlannedExpense plannedExpense;

    @OneToMany(mappedBy = "plannedExpenseDetail")
    @JsonIgnore
    private List<ExpenseMatching> expenseMatchingList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getPlannedExpenseId() {
        return plannedExpenseId;
    }

    public void setPlannedExpenseId(long plannedExpenseId) {
        this.plannedExpenseId = plannedExpenseId;
    }

    public PlannedExpense getPlannedExpense() {
        return plannedExpense;
    }

    public void setPlannedExpense(PlannedExpense plannedExpense) {
        this.plannedExpense = plannedExpense;
    }

    public List<ExpenseMatching> getExpenseMatchingList() {
        return expenseMatchingList;
    }

    public void setExpenseMatchingList(List<ExpenseMatching> expenseMatchingList) {
        this.expenseMatchingList = expenseMatchingList;
    }
}
