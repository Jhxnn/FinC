package com.FinC.repositories;

import com.FinC.models.Account;
import com.FinC.models.Expense;
import com.FinC.models.RecurringExpense;
import com.FinC.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, UUID> {

    List<RecurringExpense> findByAccount(Account account);

    List<RecurringExpense> findByAccountAndDateBetween(Account account, LocalDate startDate, LocalDate endDate);

}
