package io.hansan.mariaes.auth.data.dto;

import com.sun.istack.NotNull;

public record UserBaseSignUpDto(
        @NotNull String name,
        @NotNull String password
) {
}
