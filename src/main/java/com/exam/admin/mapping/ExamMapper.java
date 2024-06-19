package com.exam.admin.mapping;

import com.exam.admin.domain.model.entity.Exam;
import com.exam.admin.resource.exam.CreateExamResource;
import com.exam.admin.resource.exam.ExamResource;
import com.exam.admin.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ExamMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ExamResource toResource(Exam model) {
        return mapper.map(model, ExamResource.class);
    }

    public Page<ExamResource> modelListToPage(List<Exam> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ExamResource.class), pageable, modelList.size());
    }

    public List<ExamResource> modelListToResource(List<Exam> modelList) {
        return mapper.mapList(modelList, ExamResource.class);
    }

    public Exam toModel(CreateExamResource resource) {
        return mapper.map(resource, Exam.class);
    }
}
