package io.hansan.grade.controller;

import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.hansan.grade.service.GradeService;
/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:21
 * @description：TODO
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grade")
public class GradeController {
    public final GradeService gradeService;
    @GetMapping("/{id}")
    public CommonResult getGrade(@RequestParam Long id) {
        return CommonResult.success(gradeService.getGrade(id));
    }

    @GetMapping("/all/{userId}")
    public CommonResult listGrade(@RequestParam(defaultValue = "0") Long page, @RequestParam(defaultValue = "10") Long size, @RequestParam Long userId ) {
        return CommonResult.success(gradeService.listGrade(page, size, userId));
    }
}
