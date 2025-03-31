package com.FinC.repositories;

import com.FinC.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RevenueRepository extends JpaRepository<Revenue, UUID> {
}
