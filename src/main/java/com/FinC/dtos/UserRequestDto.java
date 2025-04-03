package com.FinC.dtos;

import com.FinC.models.enums.UserRole;

public record UserRequestDto(String name, String email, String password, UserRole role) {
}
