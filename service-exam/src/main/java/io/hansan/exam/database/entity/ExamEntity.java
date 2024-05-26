package io.hansan.exam.database.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 22:54
 * @description：TODO
 */
@Data
@Entity
@Table(name = "exams")
@SQLDelete(sql = "update exams set deleted = true where id = ?")
@Where(clause = "deleted = false")
public class ExamEntity {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 班级
     */
    @Column
    private Long classId;

    /**
     * 试卷
     */
    @Column(nullable = false)
    private Long pageId;

    /**
     * 考试的名称
     */
    @Column(nullable = false)
    private String name;
    /**
     * 描述
     */
    @Column(nullable = false)
    private String description;
    /**
     * 开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "end_time")
    private Date endTime;

    /**
     * 考试的总分
     */
    @Column(nullable = false, name = "total_marks")
    @ColumnDefault("100")
    private Integer totalMarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
