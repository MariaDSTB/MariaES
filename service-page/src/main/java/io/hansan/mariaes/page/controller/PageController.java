package io.hansan.mariaes.page.controller;

import io.hansan.mariaes.page.data.vo.PageVo;
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
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;
    @GetMapping("/{id}")
    public PageVo getPage(@RequestParam Long id) {
        return pageService.getPage(id);
    }

    @GetMapping("/all")
    public List<PageVo> getAllPages(@RequestParam int page, @RequestParam int size) {
        return pageService.getAllPages(page, size);
    }

    @DeleteMapping("/{id}")
    public void deletePage(@RequestParam Long id) {
        pageService.deletePage(id);
    }
}
