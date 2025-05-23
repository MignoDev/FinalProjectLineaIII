package com.example.HomeFinances.Models;

public class PlannedExpenseWithDetailDTO {
    private PlannedExpense plannedExpense;
    private PlannedExpenseDetail plannedExpenseDetail;

    public PlannedExpenseWithDetailDTO(PlannedExpense plannedExpense, PlannedExpenseDetail plannedExpenseDetail) {
        this.plannedExpense = plannedExpense;
        this.plannedExpenseDetail = plannedExpenseDetail;
    }

    public PlannedExpense getPlannedExpense() {
        return plannedExpense;
    }

    public void setPlannedExpense(PlannedExpense plannedExpense) {
        this.plannedExpense = plannedExpense;
    }

    public PlannedExpenseDetail getPlannedExpenseDetail() {
        return plannedExpenseDetail;
    }

    public void setPlannedExpenseDetail(PlannedExpenseDetail plannedExpenseDetail) {
        this.plannedExpenseDetail = plannedExpenseDetail;
    }
}
