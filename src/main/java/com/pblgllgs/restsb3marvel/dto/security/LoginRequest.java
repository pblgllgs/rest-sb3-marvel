package com.pblgllgs.restsb3marvel.dto.security;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username, @NotBlank String password
) {
}
