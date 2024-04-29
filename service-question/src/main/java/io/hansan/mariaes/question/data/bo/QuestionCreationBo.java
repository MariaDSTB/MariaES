package io.hansan.mariaes.question.data.bo;

import com.sun.istack.NotNull;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 21:09
 * @description：TODO
 */
public record QuestionCreationBo(
        String answer,
        String type,
        String content,
        String difficulty

) {
    @NotNull
    public static QuestionCreationBo fromRequest(String answer, String type, String content, String difficulty) {
        return new QuestionCreationBo(answer, type, content, difficulty);
    }
}
