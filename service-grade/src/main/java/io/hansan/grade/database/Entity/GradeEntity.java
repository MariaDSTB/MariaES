package io.hansan.grade.database.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:14
 * @description：TODO
 */
@Entity
@Data
@Table(name = "grades")
@NoArgsConstructor
@RequiredArgsConstructor
public class GradeEntity {
        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false)
        private Long examId;

        @Column(nullable = false)
        private Long studentId;

        @Column(nullable = false)
        private Integer score;

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
