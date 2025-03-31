package com.FinC.dtos;

import com.FinC.models.enums.AccountType;

import java.util.UUID;

public record AccountDto(UUID userId, AccountType type, double balance) {
}
