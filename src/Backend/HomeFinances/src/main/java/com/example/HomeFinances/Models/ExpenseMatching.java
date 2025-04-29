package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class ExpenseMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "actual_expense_id")
    private long actualExpenseId;

    @Column(name = "planned_expense_detail_id")
    private long plannedExpenseDetailId;

    @ManyToOne
    @JoinColumn(name = "actual_expense_id", updatable = false, insertable = false)
    private ActualExpense actualExpense;

    @ManyToOne
    @JoinColumn(name = "planned_expense_detail_id", insertable = false, updatable = false)
    private PlannedExpenseDetail plannedExpenseDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActualExpenseId() {
        return actualExpenseId;
    }

    public void setActualExpenseId(long actualExpenseId) {
        this.actualExpenseId = actualExpenseId;
    }

    public long getPlannedExpenseDetailId() {
        return plannedExpenseDetailId;
    }

    public void setPlannedExpenseDetailId(long plannedExpenseDetailId) {
        this.plannedExpenseDetailId = plannedExpenseDetailId;
    }

    public ActualExpense getActualExpense() {
        return actualExpense;
    }

    public void setActualExpense(ActualExpense actualExpense) {
        this.actualExpense = actualExpense;
    }

    public PlannedExpenseDetail getPlannedExpenseDetail() {
        return plannedExpenseDetail;
    }

    public void setPlannedExpenseDetail(PlannedExpenseDetail plannedExpenseDetail) {
        this.plannedExpenseDetail = plannedExpenseDetail;
    }
}
