package com.FinC.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record RevenueDto(double value, LocalDate date, String name, UUID accountId) {
}
