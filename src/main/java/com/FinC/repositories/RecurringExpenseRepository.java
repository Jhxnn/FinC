package com.FinC.repositories;

import com.FinC.models.RecurringExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpenses, UUID> {
}
