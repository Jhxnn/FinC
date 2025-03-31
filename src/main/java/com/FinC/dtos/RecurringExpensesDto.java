package com.FinC.dtos;

import com.FinC.models.enums.Frequency;

import java.time.LocalDate;
import java.util.UUID;

public record RecurringExpensesDto(double value, LocalDate date, String name, Frequency frequency, UUID accountId) {
}
