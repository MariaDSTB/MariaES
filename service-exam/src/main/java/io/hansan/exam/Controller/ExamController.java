package io.hansan.exam.Controller;

import io.hansan.exam.database.entity.ExamEntity;
import io.hansan.exam.service.ExamService;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.annotation.Validated;
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
    @CrossOrigin
    public CommonResult getExam(@RequestParam Long id) {
        return CommonResult.success(examService.getExam(id));
    }
    @PostMapping("/all")
    @CrossOrigin
    public CommonResult listExam(@RequestParam(defaultValue = "0") Long page, @RequestParam(defaultValue = "10") Long size ) {
        return CommonResult.success(examService.listExam(page, size));
    }
    @PostMapping("/add")
    @CrossOrigin
    public CommonResult addExam(@Validated @RequestBody ExamEntity examEntity) {
        examService.saveExam(examEntity);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public CommonResult deleteExam(@RequestParam Long id) {
        examService.deleteExam(id);
        return CommonResult.success();
    }
}
