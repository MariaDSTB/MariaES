package io.hansan.exam.service;

import io.hansan.exam.database.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:09
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
}
