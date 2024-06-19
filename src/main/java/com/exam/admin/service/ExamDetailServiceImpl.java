package com.exam.admin.service;

import com.exam.admin.domain.model.entity.ExamDetail;
import com.exam.admin.domain.persistence.ExamDetailRepository;
import com.exam.admin.domain.persistence.ExamRepository;
import com.exam.admin.domain.service.ExamDetailService;
import com.exam.admin.shared.exception.ResourceNotFoundException;
import com.exam.admin.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ExamDetailServiceImpl implements ExamDetailService {
    private static final String ENTITY = "ExamDetail";
    private final ExamRepository examRepository;
    private final ExamDetailRepository examDetailRepository;
    private final Validator validator;

    public ExamDetailServiceImpl(ExamRepository examRepository, ExamDetailRepository examDetailRepository, Validator validator) {
        this.examRepository = examRepository;
        this.examDetailRepository = examDetailRepository;
        this.validator = validator;
    }

    @Override
    public ExamDetail create(ExamDetail examDetail, Long examId) {
        Set<ConstraintViolation<ExamDetail>> violations = validator.validate(examDetail);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return examRepository.findById(examId).map(data -> {
            examDetail.setExam(data);
            return examDetailRepository.save(examDetail);
        }).orElseThrow(()->new ResourceNotFoundException("Exam",examId));
    }
}
