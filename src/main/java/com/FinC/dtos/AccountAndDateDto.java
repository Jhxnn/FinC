package com.FinC.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record AccountAndDateDto(UUID accountId, LocalDate startDate, LocalDate endDate) {
}
