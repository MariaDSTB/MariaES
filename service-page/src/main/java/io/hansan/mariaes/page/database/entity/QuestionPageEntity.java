package io.hansan.mariaes.page.database.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author ：何汉叁
 * @date ：2024/5/4 23:01
 * @description：TODO
 */
@Data
@Entity
@Table(name = "question_pages")
@SQLDelete(sql = "update question_pages set deleted = true where page_id = ?")
@Where(clause = "deleted = false")
public class QuestionPageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "page_id")
    private Long pageId;

    @Column(nullable = false, name = "question_id")
    private Long questionId;

    @Column
    private String content;

    @ColumnDefault("false")
    private boolean deleted;
}
