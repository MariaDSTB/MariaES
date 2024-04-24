package io.hansan.servicequestion.database.repository;

import io.hansan.servicequestion.database.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 20:18
 * @description：TODO
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE QuestionEntity q SET q.idDeleted = true WHERE q.id = ?1")
    int updateDeleteId(Long id);
}
