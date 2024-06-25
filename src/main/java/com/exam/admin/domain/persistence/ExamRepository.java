package com.exam.admin.domain.persistence;

import com.exam.admin.domain.model.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = "select g from Exam g " +
            "where (g.topicCode = :topicCode or :topicCode is null) " +
            "and (g.sectionCode = :sectionCode or :sectionCode is null) " +
            "and (g.courseCode = :courseCode or :courseCode is null) " +
            "and (g.id = :id or :id is null)")
    Page<Exam> findByFilter(
            @Param("id") Long id,
            @Param("topicCode") Long topicCode,
            @Param("sectionCode") Long sectionCode,
            @Param("courseCode") Long courseCode,
            Pageable pageable
    );
    Optional<Exam> findByTopicCodeAndSectionCodeAndCourseCode(Long topicCode, Long sectionCode, Long courseCode);
}
