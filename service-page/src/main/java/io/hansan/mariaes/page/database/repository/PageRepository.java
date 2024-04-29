package io.hansan.mariaes.page.database.repository;

import io.hansan.mariaes.page.database.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long>{
}
