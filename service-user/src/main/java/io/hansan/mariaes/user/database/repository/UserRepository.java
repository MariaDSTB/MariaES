package io.hansan.mariaes.user.database.repository;

import io.hansan.mariaes.user.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:19
 * @description：TODO
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query("select u from UserEntity u where u.name = :name")
    Optional<UserEntity> findByName(String name);
}
