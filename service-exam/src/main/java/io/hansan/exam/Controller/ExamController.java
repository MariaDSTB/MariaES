package io.hansan.exam.Controller;

import io.hansan.exam.database.entity.ExamEntity;
import io.hansan.exam.service.ExamService;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:11
 * @description：TODO
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {
    private final ExamService examService;
    @GetMapping("/{id}")
    public CommonResult getExam(@RequestParam Long id) {
        return CommonResult.success(examService.getExam(id));
    }
    @PostMapping("/list")
    public CommonResult listExam(@RequestParam(defaultValue = "0") Long page, @RequestParam(defaultValue = "10") Long size ) {
        return CommonResult.success(examService.listExam(page, size));
    }
    @PostMapping("/add")
    public CommonResult addExam(@RequestParam ExamEntity examEntity) {
        examService.saveExam(examEntity);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    public CommonResult deleteExam(@RequestParam Long id) {
        examService.deleteExam(id);
        return CommonResult.success();
    }
}
