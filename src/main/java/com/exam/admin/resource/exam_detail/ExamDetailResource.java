package com.exam.admin.resource.exam_detail;

import lombok.Data;

import java.util.List;

@Data
public class ExamDetailResource {
    private Long id;
    private String question;
    private List<String> options;
    private int correctOptionOrder;
    private Long examId;
}
