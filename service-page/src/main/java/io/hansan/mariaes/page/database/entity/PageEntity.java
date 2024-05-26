package io.hansan.mariaes.page.database.entity;

import io.hansan.mariaes.page.data.bo.PageCreateBo;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import io.hansan.mariaes.question.database.entity.QuestionEntity;
/**
 * @author ：何汉叁
 * @date ：2024/4/29 8:56
 * @description：TODO
 */
@AllArgsConstructor
@NoArgsConstructor
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

    @Column
    private String content;

    @Column
    private String answer;

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

    @NotNull
    public static PageEntity fromCreateBo(PageCreateBo pageCreateBo) {
        return new PageEntity(-1L ,pageCreateBo.title(),
                pageCreateBo.content(),
                pageCreateBo.answer(),
                Date.from(Instant.now()), Date.from(Instant.now()), false);
    }
}
