package com.FinC.repositories;

import com.FinC.models.Account;
import com.FinC.models.Expense;
import com.FinC.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RevenueRepository extends JpaRepository<Revenue, UUID> {

    List<Revenue> findByAccount(Account account);
}
