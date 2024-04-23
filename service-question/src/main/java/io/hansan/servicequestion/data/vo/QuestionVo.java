package io.hansan.servicequestion.data.vo;

import com.sun.istack.NotNull;
import io.hansan.servicequestion.database.entity.QuestionEntity;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 20:18
 * @description：TODO
 */

public record QuestionVo (
        Long id,
        String answer,
        String type,
        String content,
        String difficulty,
        String createdAt){
    @NotNull
    public static QuestionVo fromEntity(@NotNull QuestionEntity entity) {
        return new QuestionVo(
                entity.getId(),
                entity.getAnswer(), entity.getType(),
                entity.getContent(), entity.getDifficulty(),
                entity.getCreatedAt().toString());
    }
}
