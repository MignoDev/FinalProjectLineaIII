package com.example.HomeFinances.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ActualExpense {

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
    @JoinColumn(name = "type_id", updatable = false, insertable = false)
    private TypeExpense typeExpense;

    @ManyToOne
    @JoinColumn(name = "home_id", insertable = false, updatable = false)
    private Home home;

    @OneToMany(mappedBy = "actualExpense")
    @JsonIgnore
    private List<ExpenseMatching> expenseMatchingList;

    public List<ExpenseMatching> getExpenseMatchingList() {
        return expenseMatchingList;
    }

    public void setExpenseMatchingList(List<ExpenseMatching> expenseMatchingList) {
        this.expenseMatchingList = expenseMatchingList;
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
