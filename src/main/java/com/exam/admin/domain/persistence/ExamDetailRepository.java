package com.exam.admin.domain.persistence;

import com.exam.admin.domain.model.entity.ExamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDetailRepository extends JpaRepository<ExamDetail, Long> {
}
