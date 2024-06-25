package com.exam.admin.service;

import com.exam.admin.domain.model.entity.Exam;
import com.exam.admin.domain.persistence.ExamRepository;
import com.exam.admin.domain.service.ExamService;
import com.exam.admin.shared.domain.constants.ConstantsService;
import com.exam.admin.shared.exception.ResourceNotFoundException;
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
public class ExamServiceImpl implements ExamService {

    private static final String ENTITY = "Exam";
    private final ExamRepository examRepository;
    private final Validator validator;

    public ExamServiceImpl(ExamRepository examRepository, Validator validator) {
        this.examRepository = examRepository;
        this.validator = validator;
    }

    @Override
    public List<Exam> getByFilter(Map<String, Object> parameters) {
        Long id = (Long) parameters.get("id");
        Long topicCode = (Long) parameters.get("topicCode");
        Long sectionCode = (Long) parameters.get("sectionCode");
        Long courseCode = (Long) parameters.get("courseCode");

        int page = Integer.parseInt((String) parameters.get(ConstantsService.PAGE));
        int size = Integer.parseInt((String) parameters.get(ConstantsService.SIZE));
        Pageable pageable = PageRequest.of(page, size);

        return examRepository.findByFilter(id, topicCode, sectionCode, courseCode, pageable).getContent();
    }

    @Override
    public Exam create(Exam exam, Long topicId, Long sectionId, Long courseId) {
        Set<ConstraintViolation<Exam>> violations = validator.validate(exam);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        examRepository.findByTopicCodeAndSectionCodeAndCourseCode(topicId, sectionId, courseId)
                .ifPresent(item -> {
                    this.delete(item.getId());
                });
        return examRepository.save(exam);
    }

    @Override
    public ResponseEntity<?> delete(Long examId) {
        return examRepository.findById(examId).map(item -> {
            examRepository.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, examId));
    }
}
