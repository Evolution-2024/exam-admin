package com.exam.admin.mapping;

import com.exam.admin.domain.model.entity.ExamDetail;
import com.exam.admin.resource.exam_detail.CreateExamDetailResource;
import com.exam.admin.resource.exam_detail.ExamDetailResource;
import com.exam.admin.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ExamDetailMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ExamDetailResource toResource(ExamDetail model) {
        return mapper.map(model, ExamDetailResource.class);
    }

    public Page<ExamDetailResource> modelListToPage(List<ExamDetail> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ExamDetailResource.class), pageable, modelList.size());
    }

    public List<ExamDetailResource> modelListToResource(List<ExamDetail> modelList) {
        return mapper.mapList(modelList, ExamDetailResource.class);
    }

    public ExamDetail toModel(CreateExamDetailResource resource) {
        return mapper.map(resource, ExamDetail.class);
    }
}