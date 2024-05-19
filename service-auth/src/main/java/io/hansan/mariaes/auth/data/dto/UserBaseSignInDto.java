package io.hansan.mariaes.auth.data.dto;

public record UserBaseSignInDto(
        String username,
        String password
) {
}
