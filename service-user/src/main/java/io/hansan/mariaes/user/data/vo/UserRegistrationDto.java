package io.hansan.mariaes.user.data.vo;

import javax.validation.constraints.NotNull;

public record UserRegistrationDto(
        @NotNull String name,
        @NotNull String password
) {
}
