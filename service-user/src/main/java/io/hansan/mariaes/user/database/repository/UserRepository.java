package io.hansan.mariaes.user.database.repository;

import io.hansan.mariaes.user.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:19
 * @description：TODO
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
}
