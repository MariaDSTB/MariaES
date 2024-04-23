package io.hansan.servicequestion.controller;

import io.hansan.servicequestion.data.bo.QuestionCreationBo;
import io.hansan.servicequestion.data.vo.QuestionVo;
import io.hansan.servicequestion.database.entity.QuestionEntity;
import io.hansan.servicequestion.service.QuestionService;
import lombok.RequiredArgsConstructor;
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
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/all")
    public List<QuestionVo> getAllQuestions(@RequestParam int page, @RequestParam int size) {
        return questionService.getAllQuestions(page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
    @GetMapping("{id}")
    public QuestionVo getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }
    @PostMapping("/add")
    public void addQuestion(@RequestBody QuestionEntity questionEntity) {
        questionService.addQuestion(questionEntity);
    }
}
