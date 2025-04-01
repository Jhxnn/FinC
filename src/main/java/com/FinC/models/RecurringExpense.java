package com.FinC.models;

import com.FinC.models.enums.Frequency;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "recurring_expenses")
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID recurringExpensesId;

    private double value;

    private LocalDate date;

    private String name;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private Account account;

    private String pix;

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

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
