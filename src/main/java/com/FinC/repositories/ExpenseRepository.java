package com.FinC.repositories;

import com.FinC.models.Account;
import com.FinC.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    List<Expense> findByAccount(Account account);
}
