package com.FinC.repositories;

import com.FinC.models.Account;
import com.FinC.models.Expense;
import com.FinC.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

    List<Goal> findByAccount(Account account);
}
