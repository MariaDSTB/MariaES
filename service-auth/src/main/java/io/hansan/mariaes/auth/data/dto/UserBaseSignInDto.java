package io.hansan.mariaes.auth.data.dto;

import com.sun.istack.NotNull;

public record UserBaseSignInDto(
        @NotNull String name,
        @NotNull String password
) {
}
