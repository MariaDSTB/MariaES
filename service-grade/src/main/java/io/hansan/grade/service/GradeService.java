package io.hansan.grade.service;

import io.hansan.grade.data.vo.GradeVo;
import io.hansan.grade.database.Entity.GradeEntity;
import io.hansan.grade.database.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:20
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class GradeService {
    public final GradeRepository gradeRepository;

    public GradeVo getGrade(Long id)  {
        Optional<GradeEntity> gradeEntityOptional = gradeRepository.findById(id);
        if (gradeEntityOptional.isPresent()) {
            GradeEntity gradeEntity = gradeEntityOptional.get();
            return GradeVo.fromEntity("name", gradeEntity.getScore(), "studentName");
        }else
            return null;
    }
}
