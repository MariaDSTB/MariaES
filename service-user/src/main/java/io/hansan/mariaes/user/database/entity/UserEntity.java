package io.hansan.mariaes.user.database.entity;

import io.hansan.mariaes.user.data.vo.UserRegistrationDto;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:17
 * @description：TODO
 */
@Data
@Entity
@Table(name = "users")
@SQLDelete(sql  = "UPDATE users set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "create_at",updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "update_at")
    private Date updatedAt;

    @ColumnDefault("false")
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public UserEntity fromBaseUserRegistrationDto(UserRegistrationDto baseUserRegistrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(baseUserRegistrationDto.name());
        userEntity.setPassword(baseUserRegistrationDto.password());
        return userEntity;
    }
}
