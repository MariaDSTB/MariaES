package io.hansan.exam.data.vo;

public record ExamVo(
        String name,
        String description,
        String startTime,
        String endTime,
        String classname
) {
}
