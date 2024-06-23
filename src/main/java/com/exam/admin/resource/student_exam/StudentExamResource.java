package com.exam.admin.resource.student_exam;

import lombok.Data;

@Data
public class StudentExamResource {
    private Long id;
    private Long studentCode;
    private String studentUsername;
    private Long examCode;
    private Long topicCode;
    private Long sectionCode;
    private Long courseCode;
    private Double note;
}