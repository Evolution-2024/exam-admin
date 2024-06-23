package com.exam.admin.api;

import com.exam.admin.domain.service.StudentExamService;
import com.exam.admin.mapping.StudentExamMapper;
import com.exam.admin.resource.student_exam.CreateStudentExamResource;
import com.exam.admin.resource.student_exam.StudentExamResource;
import com.exam.admin.shared.domain.constants.ConstantsService;
import com.exam.admin.shared.domain.constants.DefaultParams;
import com.exam.admin.shared.domain.service.communication.BaseResponse;
import com.exam.admin.shared.exception.ResourceNotFoundException;
import com.exam.admin.shared.exception.ResourceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student-exam")
public class StudentExamController {
    private final StudentExamService studentExamService;
    private final StudentExamMapper mapper;

    public StudentExamController(StudentExamService studentExamService, StudentExamMapper mapper) {
        this.studentExamService = studentExamService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<StudentExamResource>>> getAllStudentExams(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long topicCode,
            @RequestParam(required = false) Long sectionCode,
            @RequestParam(required = false) Long courseCode,
            @RequestParam(required = false) Long studentCode,
            @RequestParam(required = false) Long examCode,
            @RequestParam(defaultValue = DefaultParams.PAGE) String page,
            @RequestParam(defaultValue = DefaultParams.SIZE) String size
    ) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("topicCode", topicCode);
        parameters.put("sectionCode", sectionCode);
        parameters.put("courseCode", courseCode);
        parameters.put("studentCode", studentCode);
        parameters.put("examCode", examCode);

        parameters.put(ConstantsService.PAGE, page);
        parameters.put(ConstantsService.SIZE, size);

        BaseResponse<List<StudentExamResource>> response = null;

        try {
            List<StudentExamResource> list = mapper.modelListToResource(studentExamService.getByFilter(parameters));
            response = new BaseResponse<>(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new BaseResponse<>(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse<StudentExamResource>> createStudentExam(@RequestBody CreateStudentExamResource request) {
        BaseResponse<StudentExamResource> response = null;
        try {
            var studentExam = studentExamService.create(mapper.toModel(request));
            StudentExamResource resource = mapper.toResource(studentExam);
            response = new BaseResponse<>(resource);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceValidationException | ResourceNotFoundException e) {
            response = new BaseResponse<>(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable Long examId) {
        try {
            return studentExamService.delete(examId);
        } catch (ResourceValidationException | ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("average")
    public ResponseEntity<BaseResponse<Double>> getAverage(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long topicCode,
            @RequestParam(required = false) Long sectionCode,
            @RequestParam(required = false) Long courseCode,
            @RequestParam(required = false) Long studentCode,
            @RequestParam(required = false) Long examCode
    ) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("topicCode", topicCode);
        parameters.put("sectionCode", sectionCode);
        parameters.put("courseCode", courseCode);
        parameters.put("studentCode", studentCode);
        parameters.put("examCode", examCode);

        BaseResponse<Double> response = null;
        try {
            Double avg = studentExamService.getAverage(parameters);
            response = new BaseResponse<>(avg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new BaseResponse<>(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
