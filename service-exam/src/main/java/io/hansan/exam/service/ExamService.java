package io.hansan.exam.service;

import io.hansan.exam.data.vo.ExamVo;
import io.hansan.exam.database.entity.ExamEntity;
import io.hansan.exam.database.repository.ExamRepository;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:09
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public ExamVo getExam(Long id) {
        return examRepository.findById(id).map(ExamVo::fromEntity).orElse(null);
    }

    public List<ExamVo> listExam(Long page, Long size) {
        return examRepository.findAll(PageRequest.of(page.intValue(), size.intValue(), Sort.by("created_at").descending()))
                .stream()
                .map(ExamVo::fromEntity)
                .toList();
    }

    public void saveExam(ExamEntity examEntity) {
        examRepository.save(examEntity);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}
