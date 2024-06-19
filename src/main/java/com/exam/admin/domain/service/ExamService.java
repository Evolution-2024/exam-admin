package com.exam.admin.domain.service;

import com.exam.admin.domain.model.entity.Exam;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ExamService {
    List<Exam> getByFilter(Map<String, Object> parameters);
    Exam create(Exam exam, Long topicId, Long sectionId, Long courseId);
    ResponseEntity<?> delete(Long examId);
}
