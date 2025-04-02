package com.FinC.dtos;

import java.util.UUID;

public record UserResponseDto(String email, String name, UUID userId) {
}
