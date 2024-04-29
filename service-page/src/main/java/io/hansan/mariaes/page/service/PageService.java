package io.hansan.mariaes.page.service;

import io.hansan.mariaes.page.data.vo.PageVo;
import io.hansan.mariaes.page.database.repository.PageRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/4/29 9:15
 * @description：TODO
 */
@RequiredArgsConstructor
@Service
public class PageService {
    private final PageRepository pageRepository;
    public PageVo getPage(Long id) {
        return pageRepository.findById(id).map(PageVo::FromQuestion).orElse(null);
    }
}
