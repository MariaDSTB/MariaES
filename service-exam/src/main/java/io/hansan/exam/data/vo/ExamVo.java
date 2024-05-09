package io.hansan.exam.data.vo;

import io.hansan.exam.database.entity.ExamEntity;

public record ExamVo(
        Long pageId,
        String name,
        String description,
        String startTime,
        String endTime
) {
    public static ExamVo fromEntity(ExamEntity examEntity) {
        return new ExamVo(examEntity.getPageId(), examEntity.getName(), examEntity.getDescription(), examEntity.getStartTime().toString(), examEntity.getEndTime().toString());
    }
}
