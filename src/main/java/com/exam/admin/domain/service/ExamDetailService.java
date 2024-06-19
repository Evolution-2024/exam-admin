package com.exam.admin.domain.service;

import com.exam.admin.domain.model.entity.ExamDetail;

public interface ExamDetailService {
    ExamDetail create(ExamDetail examDetail, Long examId);
}
