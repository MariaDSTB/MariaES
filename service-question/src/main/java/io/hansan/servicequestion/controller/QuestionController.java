package io.hansan.servicequestion.controller;

import io.hansan.servicequestion.data.bo.QuestionCreationBo;
import io.hansan.servicequestion.data.vo.QuestionVo;
import io.hansan.servicequestion.database.entity.QuestionEntity;
import io.hansan.servicequestion.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 19:45
 * @description：TODO
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
@Api(tags = "Question")
public class QuestionController {
    private final QuestionService questionService;
    @ApiOperation(value = "获取所有题库")
    @GetMapping("/all")
    public List<QuestionVo> getAllQuestions(@RequestParam int page, @RequestParam int size) {
        return questionService.getAllQuestions(page, size);
    }

    @ApiOperation( value = "删除题目")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 (OK) or 204 (NO CONTENT) or 202 (ACCEPTED) or 201 (CREATED) or 200 (OK
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @ApiOperation(value = "获取题目")
    @GetMapping("{id}")
    public QuestionVo getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @ApiOperation(value = "添加题目")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@RequestBody QuestionEntity questionEntity) {
        questionService.addQuestion(questionEntity);
    }
}
