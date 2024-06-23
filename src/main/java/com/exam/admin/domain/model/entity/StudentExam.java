package com.exam.admin.domain.model.entity;

import com.exam.admin.shared.domain.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Data
@Table(name = "student_exams")
public class StudentExam extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentCode;
    private String studentUsername;
    private Long examCode;
    private Long topicCode;
    private Long sectionCode;
    private Long courseCode;
    private Double note;
}
