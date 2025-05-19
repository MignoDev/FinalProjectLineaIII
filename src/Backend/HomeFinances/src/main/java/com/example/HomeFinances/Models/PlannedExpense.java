package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
public class PlannedExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private double amount;

    private String comment;

    @Column(name = "type_id")
    private long typeId;

    @Column(name = "home_id")
    private long homeId;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    @JsonIgnore
    private TypeExpense typeExpense;

    @ManyToOne
    @JoinColumn(name = "home_id", updatable = false, insertable = false)
    @JsonIgnore
    private Home home;

    @OneToMany(mappedBy = "plannedExpense")
    @JsonIgnore
    private List<PlannedExpenseDetail> plannedExpenseDetails;

    public List<PlannedExpenseDetail> getPlannedExpenseDetails() {
        return plannedExpenseDetails;
    }

    public void setPlannedExpenseDetails(List<PlannedExpenseDetail> plannedExpenseDetails) {
        this.plannedExpenseDetails = plannedExpenseDetails;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public TypeExpense getTypeExpense() {
        return typeExpense;
    }

    public void setTypeExpense(TypeExpense typeExpense) {
        this.typeExpense = typeExpense;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
