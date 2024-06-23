package com.exam.admin.mapping;

import com.exam.admin.domain.model.entity.StudentExam;
import com.exam.admin.resource.student_exam.CreateStudentExamResource;
import com.exam.admin.resource.student_exam.StudentExamResource;
import com.exam.admin.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class StudentExamMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public StudentExamResource toResource(StudentExam model) {
        return mapper.map(model, StudentExamResource.class);
    }

    public Page<StudentExamResource> modelListToPage(List<StudentExam> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, StudentExamResource.class), pageable, modelList.size());
    }

    public List<StudentExamResource> modelListToResource(List<StudentExam> modelList) {
        return mapper.mapList(modelList, StudentExamResource.class);
    }

    public StudentExam toModel(CreateStudentExamResource resource) {
        return mapper.map(resource, StudentExam.class);
    }
}
