package com.FinC.dtos;

import com.FinC.models.enums.UserRole;

import java.util.UUID;

public record UserResponseDto(String email, String name, UUID userId, UserRole role) {
}
