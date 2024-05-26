package io.hansan.exam.data.vo;

import io.hansan.exam.database.entity.ExamEntity;

public record ExamVo(
        Long pageId,
        String title,
        String startTime
) {
    public static ExamVo fromEntity(ExamEntity examEntity) {
        return new ExamVo(examEntity.getPageId(), examEntity.getTitle(), examEntity.getStartTime().toString());
    }
}
