package io.hansan.mariaes.page.data.bo;

import javax.validation.constraints.NotNull;

/**
 * @author ：何汉叁
 * @date ：2024/5/5 23:18
 * @description：TODO
 */
public record QuestionPageCreateBo(
        Long pageId,
        Long questionId
) {
    @NotNull
    public static QuestionPageCreateBo fromCreateDto(Long pageId, QuestionPageCreateBo questionPageCreateBo) {
        return new QuestionPageCreateBo(pageId, questionPageCreateBo.questionId());
    }
}
