package com.FinC.repositories;

import com.FinC.models.Account;
import com.FinC.models.Expense;
import com.FinC.models.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, UUID> {
    List<RecurringExpense> findByAccount(Account account);
}
