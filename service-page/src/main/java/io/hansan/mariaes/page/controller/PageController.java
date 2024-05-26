package io.hansan.mariaes.page.controller;

import io.hansan.mariaes.common.data.CommonResult;
import io.hansan.mariaes.page.data.dto.PageCreateDto;
import io.hansan.mariaes.page.data.vo.PageVo;
import io.hansan.mariaes.page.database.entity.PageEntity;
import io.hansan.mariaes.page.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/29 9:17
 * @description：TODO
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/page")
public class PageController {
    private final PageService pageService;
    @GetMapping("/{id}")
    @CrossOrigin
    public PageVo getPage(@RequestParam Long id) {
        return pageService.getPage(id);
    }

    @GetMapping("/all")
    @CrossOrigin
    public CommonResult getAllPages(@RequestParam int page, @RequestParam int size) {
        return CommonResult.success(pageService.getAllPages(page, size));
    }

    @PostMapping("/add")
    @CrossOrigin
    public CommonResult addPage(@RequestBody PageCreateDto pageCreateDto) {
        pageService.addPage(pageCreateDto);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public CommonResult deletePage(@RequestParam Long id) {
        pageService.deletePage(id);
        return CommonResult.success();
    }
}
