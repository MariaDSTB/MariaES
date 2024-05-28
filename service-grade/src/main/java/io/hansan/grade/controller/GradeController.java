package io.hansan.grade.controller;

import io.hansan.grade.data.dto.GradeCreateDto;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin
    public CommonResult getGrade(@RequestParam Long id) {
        return CommonResult.success(gradeService.getGrade(id));
    }

    @GetMapping("/all/{userId}")
    @CrossOrigin
    public CommonResult listGrade(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "1") Long userId ) {
        return CommonResult.success(gradeService.listGrade(page, size, userId));
    }

    @PostMapping("/add")
    @CrossOrigin
    public CommonResult addGrade(@RequestBody GradeCreateDto gradeCreateDto) {
        gradeService.addGrade(gradeCreateDto);
        return CommonResult.success();
    }
}
