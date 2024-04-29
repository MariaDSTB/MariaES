package io.hansan.mariaes.page.database.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import io.hansan.mariaes.question.database.entity.QuestionEntity;
/**
 * @author ：何汉叁
 * @date ：2024/4/29 8:56
 * @description：TODO
 */
@Entity
@Data
@Table(name = "pages")
@SQLDelete(sql = "update pages set deleted = true where id = ?")
@Where(clause = "deleted = false")
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany
    private List<QuestionEntity> questions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at",nullable = false, updatable = false)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at",nullable = false)
    private Date updateAi;

    @ColumnDefault("false")
    private boolean deleted;
    @PrePersist
    protected void onCreate() {
        createAt = new Date();
        updateAi = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAi = new Date();
    }
}
