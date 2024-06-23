package com.exam.admin.domain.service;

import com.exam.admin.domain.model.entity.StudentExam;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentExamService {
    List<StudentExam> getByFilter(Map<String, Object> parameters);
    StudentExam create(StudentExam studentExam);
    ResponseEntity<?> delete(Long studentExamId);
    Double getAverage(Map<String, Object> parameters);
}
