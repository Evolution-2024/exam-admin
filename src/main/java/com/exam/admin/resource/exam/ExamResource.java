package com.exam.admin.resource.exam;

import com.exam.admin.resource.exam_detail.ExamDetailResource;
import lombok.Data;

import java.util.List;

@Data
public class ExamResource {
    private Long id;
    private Long topicCode;
    private Long sectionCode;
    private Long courseCode;
    private List<ExamDetailResource> examDetails;
}
