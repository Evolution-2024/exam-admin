package com.exam.admin.service;

import com.exam.admin.domain.model.entity.StudentExam;
import com.exam.admin.domain.persistence.StudentExamRepository;
import com.exam.admin.domain.service.StudentExamService;
import com.exam.admin.shared.domain.constants.ConstantsService;
import com.exam.admin.shared.exception.ResourceValidationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StudentExamServiceImpl implements StudentExamService {
    private static final String ENTITY = "StudentExam";
    private final StudentExamRepository studentExamRepository;
    private final Validator validator;

    public StudentExamServiceImpl(StudentExamRepository studentExamRepository, Validator validator) {
        this.studentExamRepository = studentExamRepository;
        this.validator = validator;
    }

    @Override
    public List<StudentExam> getByFilter(Map<String, Object> parameters) {
        Long id = (Long) parameters.get("id");
        Long topicCode = (Long) parameters.get("topicCode");
        Long sectionCode = (Long) parameters.get("sectionCode");
        Long courseCode = (Long) parameters.get("courseCode");
        Long studentCode = (Long) parameters.get("studentCode");
        Long examCode = (Long) parameters.get("examCode");

        int page = Integer.parseInt((String) parameters.get(ConstantsService.PAGE));
        int size = Integer.parseInt((String) parameters.get(ConstantsService.SIZE));
        Pageable pageable = PageRequest.of(page, size);
        return studentExamRepository.findByFilter(id, topicCode, sectionCode, courseCode, studentCode, examCode, pageable).getContent();
    }

    @Override
    public StudentExam create(StudentExam studentExam) {
        Set<ConstraintViolation<StudentExam>> violations = validator.validate(studentExam);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return studentExamRepository.save(studentExam);
    }

    @Override
    public ResponseEntity<?> delete(Long studentExamId) {
        studentExamRepository.deleteById(studentExamId);
        return ResponseEntity.ok().build();
    }

    @Override
    public Double getAverage(Map<String, Object> parameters) {
        Long id = (Long) parameters.get("id");
        Long topicCode = (Long) parameters.get("topicCode");
        Long sectionCode = (Long) parameters.get("sectionCode");
        Long courseCode = (Long) parameters.get("courseCode");
        Long studentCode = (Long) parameters.get("studentCode");
        Long examCode = (Long) parameters.get("examCode");

        return studentExamRepository.averageNote(id, topicCode, sectionCode, courseCode, studentCode, examCode);
    }
}
