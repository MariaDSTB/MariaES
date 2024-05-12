package io.hansan.grade.data.vo;

import io.hansan.grade.database.Entity.GradeEntity;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:21
 * @description：TODO
 */
public record GradeVo(
        String name,
        Integer score,
        String studentName
) {
    public static GradeVo fromEntity(String name, Integer score, String studentName) {
        return new GradeVo(name, score, studentName);
    }
}
