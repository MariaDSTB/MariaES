package io.hansan.grade.database.Entity;

import io.hansan.grade.data.dto.GradeCreateDto;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class GradeEntity {
        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false)
        private Long examId;

        @Column(nullable = false)
        private Long studentId;

        @Column(nullable = false)
        private Long score;

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

        public static GradeEntity fromGradeDto(GradeCreateDto gradeCreateDto) {
            return new GradeEntity(
                    -1L,
                    1L,
                    1L,
                   gradeCreateDto.score(),
                    new Date(),
                    new Date());
        }
    }
