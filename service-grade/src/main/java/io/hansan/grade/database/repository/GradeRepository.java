package io.hansan.grade.database.repository;

import io.hansan.grade.data.vo.GradeVo;
import io.hansan.grade.database.Entity.GradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:19
 * @description：TODO
 */
@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

    Page<GradeEntity> findAllByStudentId(Long userId, Pageable pageable);
}
