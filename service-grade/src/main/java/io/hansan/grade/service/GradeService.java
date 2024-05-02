package io.hansan.grade.service;

import io.hansan.grade.database.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:20
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class GradeService {
    public final GradeRepository gradeRepository;
}
