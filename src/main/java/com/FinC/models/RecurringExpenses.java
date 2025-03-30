package com.FinC.models;

import jakarta.persistence.*;
import jdk.jfr.Frequency;

import java.time.LocalDate;
import java.util.UUID;

public class RecurringExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID recurringExpensesId;

    private double value;

    private LocalDate date;

    private String name;

    private Frequency frequency;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private Account account;


    public UUID getRecurringExpensesId() {
        return recurringExpensesId;
    }

    public void setRecurringExpensesId(UUID recurringExpensesId) {
        this.recurringExpensesId = recurringExpensesId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
