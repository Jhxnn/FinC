package com.FinC.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record GoalDto(double value, LocalDate date, UUID accountId) {
}
