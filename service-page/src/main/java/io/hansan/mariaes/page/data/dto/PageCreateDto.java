package io.hansan.mariaes.page.data.dto;

import java.util.List;

public record PageCreateDto(
        String title,
       String content,
       String answer
) {
}
