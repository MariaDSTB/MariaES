package io.hansan.mariaes.page.data.dto;

import java.util.List;

public record PageCreateDto(
        Long id,
        String title,
        List<Long> questionIds,
        List<String> content
) {
}
