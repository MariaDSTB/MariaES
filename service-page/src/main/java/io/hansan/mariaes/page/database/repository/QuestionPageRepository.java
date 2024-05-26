package io.hansan.mariaes.page.database.repository;

import io.hansan.mariaes.page.database.entity.QuestionPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionPageRepository extends JpaRepository<QuestionPageEntity, Long> {

//    @Query("select q.content, q.question_id from questions q join question_pages qp on q.id = qp.questionId where qp.pageId = ?1")
//    List<QuestionPageEntity> findAllByPageId(Long pageId);
}
