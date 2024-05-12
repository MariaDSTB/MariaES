package io.hansan.mariaes.page.data.bo;

import io.hansan.mariaes.page.data.dto.PageCreateDto;

import javax.validation.constraints.NotNull;

public record PageCreateBo(
        String title
) {
    @NotNull
    public static PageCreateBo fromCreateDto(PageCreateDto pageCreateDto) {
        return new PageCreateBo(pageCreateDto.title());
    }
}
