package io.hansan.grade.data.dto;

/**
 * @author ：何汉叁
 * @date ：2024/5/26 23:15
 * @description：TODO
 */
public record GradeCreateDto(
        Long studentId,
        Long examId,
        Long score
) {
}
