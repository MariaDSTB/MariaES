package io.hansan.mariaes.auth.data.dto;

public record UserSignInDto(
        String username,
        String password
) {
}
