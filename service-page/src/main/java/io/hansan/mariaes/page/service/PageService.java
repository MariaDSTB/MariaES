package io.hansan.mariaes.page.service;

import io.hansan.mariaes.page.data.vo.PageVo;
import io.hansan.mariaes.page.database.entity.PageEntity;
import io.hansan.mariaes.page.database.repository.PageRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PageVo> getAllPages(int page, int size) {
        return pageRepository.findAll(PageRequest.of(page, size, Sort.by("create_at").descending())).
                stream().map(PageVo::FromQuestion).toList();
    }

    public void deletePage(Long id) {
        pageRepository.deleteById(id);
    }

    public void addPage(PageEntity pageEntity) {
        pageRepository.save(pageEntity);
    }
}
