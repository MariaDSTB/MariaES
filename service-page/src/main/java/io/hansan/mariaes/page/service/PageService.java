package io.hansan.mariaes.page.service;

import io.hansan.mariaes.page.data.bo.PageCreateBo;
import io.hansan.mariaes.page.data.bo.QuestionPageCreateBo;
import io.hansan.mariaes.page.data.dto.PageCreateDto;
import io.hansan.mariaes.page.data.vo.PageVo;
import io.hansan.mariaes.page.database.entity.PageEntity;
import io.hansan.mariaes.page.database.entity.QuestionPageEntity;
import io.hansan.mariaes.page.database.repository.PageRepository;
import io.hansan.mariaes.page.database.repository.QuestionPageRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：何汉叁
 * @date ：2024/4/29 9:15
 * @description：TODO
 */
@RequiredArgsConstructor
@Service
public class PageService {
    private final PageRepository pageRepository;
    private QuestionPageRepository questionPageRepository;

//    public PageVo getPage(Long id) {
//        return pageRepository.findById(id).map(pageEntity -> {
//            List<QuestionPageEntity> questionPageEntities = questionPageRepository.findAllByPageId(id);
//            return PageVo.FromQuestionPage(pageEntity, questionPageEntities);
//        }).orElse(null);
//    }

    public PageVo getPage(Long id) {
        return pageRepository.findById(id).map(pageEntity -> {
            return PageVo.FromQuestionPage(pageEntity);
        }).orElse(null);
    }
//    public List<PageVo> getAllPages(int page, int size) {
//        return pageRepository.findAll(PageRequest.of(page, size, Sort.by("create_at").descending()))
//                .stream()
//                .map(PageEntity -> {
//                    List<QuestionPageEntity> questionPageEntities = questionPageRepository.findAllByPageId(PageEntity.getId());
//                    return PageVo.FromQuestionPage(PageEntity, questionPageEntities);
//                })
//                .toList();
//    }

    public List<PageVo> getAllPages(int page, int size) {
        return pageRepository.findAll(PageRequest.of(page, size, Sort.by("create_at").descending()))
                .stream()
                .map(PageEntity -> {
                    return PageVo.FromQuestionPage(PageEntity);
                })
                .toList();
    }

    public void deletePage(Long id) {
        pageRepository.deleteById(id);
    }

    /**
     * 1. 分离出PageCreateDto中的问题id，创建PageEntity和QuestionPageEntity
     * 2. 通过Bo和Entity进行转换春初导数据库
     * 3. 创建stream流，将问题id转换为QuestionPageEntity
     * @param pageCreateDto
     */
    @Transactional
//    public void addPage(PageCreateDto pageCreateDto) {
//        PageCreateBo pageCreateBo = PageCreateBo.fromCreateDto(pageCreateDto);
//        PageEntity pageEntity = PageEntity.fromCreateBo(pageCreateBo);
//        PageEntity savePageEntity = pageRepository.save(pageEntity);
//        Long pageId = savePageEntity.getId();
//        List<Long> questionIds = pageCreateDto.questionIds();
//        List<QuestionPageEntity> questionPageEntities = questionIds
//                .stream()
//                .map(
//                        questionId -> {
//                            QuestionPageCreateBo questionPageCreateBo = new QuestionPageCreateBo(pageId, questionId);
//                            return QuestionPageEntity.fromCreateBo(questionPageCreateBo);
//                        })
//                .toList();
//        questionPageRepository.saveAll(questionPageEntities);
//    }
    public void addPage(PageCreateDto pageCreateDto) {
        PageCreateBo pageCreateBo = PageCreateBo.fromCreateDto(pageCreateDto);
        PageEntity pageEntity = PageEntity.fromCreateBo(pageCreateBo);
        pageRepository.save(pageEntity);
    }
}
