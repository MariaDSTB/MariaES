package io.hansan.grade.data.vo;

import io.hansan.grade.database.Entity.GradeEntity;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:21
 * @description：TODO
 */
public record GradeVo(
        String name,
        Long score,
        String studentName
) {
    public static GradeVo fromEntity(GradeEntity gradeEntity) {
        return new GradeVo(gradeEntity.getExamId().toString(), gradeEntity.getScore(), gradeEntity.getStudentId().toString());
    }

    public static GradeVo fromGrpc (String name,Long score, String studentName) {
        return new GradeVo(name, score, studentName);
    }
}
