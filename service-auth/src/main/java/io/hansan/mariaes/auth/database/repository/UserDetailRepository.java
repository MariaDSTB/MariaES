package io.hansan.mariaes.auth.database.repository;

import io.hansan.mariaes.auth.database.Entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {
    @Query("select u from user_detail u where u.username = ?1")
    Optional<UserDetails> findByUsername(String username);
}
