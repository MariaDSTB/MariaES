package io.hansan.servicequestion.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.Date;


/**
 * @author ：何汉叁
 * @date ：2024/4/23 20:01
 * @description：TODO
 */
@Entity
@Data
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    @Id
    @Generated
    private Long id;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String difficulty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @ColumnDefault("false")
    @Column(name = "is_deleted", nullable = false)
    private boolean idDeleted;
}
