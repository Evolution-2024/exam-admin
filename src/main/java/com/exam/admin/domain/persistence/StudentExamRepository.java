package com.exam.admin.domain.persistence;

import com.exam.admin.domain.model.entity.StudentExam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long>{
    @Query(value = "select g from StudentExam g " +
            "where (g.topicCode = :topicCode or :topicCode is null) " +
            "and (g.sectionCode = :sectionCode or :sectionCode is null) " +
            "and (g.courseCode = :courseCode or :courseCode is null) " +
            "and (g.studentCode = :studentCode or :studentCode is null) " +
            "and (g.examCode = :examCode or :examCode is null) " +
            "and (g.id = :id or :id is null)")
    Page<StudentExam> findByFilter(
            @Param("id") Long id,
            @Param("topicCode") Long topicCode,
            @Param("sectionCode") Long sectionCode,
            @Param("courseCode") Long courseCode,
            @Param("studentCode") Long studentCode,
            @Param("examCode") Long examCode,
            Pageable pageable
    );
    @Query(value = "select AVG(g.note) from StudentExam g " +
            "where (g.topicCode = :topicCode or :topicCode is null) " +
            "and (g.sectionCode = :sectionCode or :sectionCode is null) " +
            "and (g.courseCode = :courseCode or :courseCode is null) " +
            "and (g.studentCode = :studentCode or :studentCode is null) " +
            "and (g.examCode = :examCode or :examCode is null) " +
            "and (g.id = :id or :id is null)")
    Double averageNote(
            @Param("id") Long id,
            @Param("topicCode") Long topicCode,
            @Param("sectionCode") Long sectionCode,
            @Param("courseCode") Long courseCode,
            @Param("studentCode") Long studentCode,
            @Param("examCode") Long examCode
    );
}
