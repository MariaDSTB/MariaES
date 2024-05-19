package io.hansan.mariaes.auth.data.dto;

import javax.validation.constraints.NotNull;

public record UserBaseSignUpDto(
        @NotNull String name,
        @NotNull String password
) {
}
