package com.FinC.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "revenues")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID revenueId;

    private double value;

    private LocalDate date;

    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "account_id")
    private Account account;

    public UUID getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(UUID revenueId) {
        this.revenueId = revenueId;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
