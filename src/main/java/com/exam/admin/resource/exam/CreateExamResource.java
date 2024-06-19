package com.exam.admin.resource.exam;

import com.exam.admin.resource.exam_detail.CreateExamDetailResource;
import lombok.Data;

import java.util.List;

@Data
public class CreateExamResource {
    private Long topicCode;
    private Long sectionCode;
    private Long courseCode;
    List<CreateExamDetailResource> examDetailResources;
}
