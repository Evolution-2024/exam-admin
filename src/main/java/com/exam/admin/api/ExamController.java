package com.exam.admin.api;

import com.exam.admin.domain.model.entity.ExamDetail;
import com.exam.admin.domain.service.ExamDetailService;
import com.exam.admin.domain.service.ExamService;
import com.exam.admin.mapping.ExamDetailMapper;
import com.exam.admin.mapping.ExamMapper;
import com.exam.admin.resource.exam.CreateExamResource;
import com.exam.admin.resource.exam.ExamResource;
import com.exam.admin.resource.exam_detail.ExamDetailResource;
import com.exam.admin.shared.domain.constants.ConstantsService;
import com.exam.admin.shared.domain.constants.DefaultParams;
import com.exam.admin.shared.domain.service.communication.BaseResponse;
import com.exam.admin.shared.exception.ResourceNotFoundException;
import com.exam.admin.shared.exception.ResourceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {
    private final ExamService examService;
    private final ExamDetailService examDetailService;
    private final ExamMapper mapper;
    private final ExamDetailMapper examDetailMapper;

    public ExamController(ExamService examService, ExamDetailService examDetailService, ExamMapper mapper, ExamDetailMapper examDetailMapper) {
        this.examService = examService;
        this.examDetailService = examDetailService;
        this.mapper = mapper;
        this.examDetailMapper = examDetailMapper;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ExamResource>>> getAllAnnouncements(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long topicCode,
            @RequestParam(required = false) Long sectionCode,
            @RequestParam(required = false) Long courseCode,
            @RequestParam(defaultValue = DefaultParams.PAGE) String page,
            @RequestParam(defaultValue = DefaultParams.SIZE) String size
    ) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("topicCode", topicCode);
        parameters.put("sectionId", sectionCode);
        parameters.put("courseCode", courseCode);

        parameters.put(ConstantsService.PAGE, page);
        parameters.put(ConstantsService.SIZE, size);

        BaseResponse<List<ExamResource>> response = null;

        try {
            List<ExamResource> list = mapper.modelListToResource(examService.getByFilter(parameters));
            response = new BaseResponse<>(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new BaseResponse<>(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ExamResource createExam(@RequestBody CreateExamResource request) {

        var exam = mapper.toResource(examService.create(mapper.toModel(request),request.getTopicCode(), request.getSectionCode(), request.getCourseCode()));

        List<ExamDetailResource> examDetailResources = new ArrayList<>();

        request.getExamDetailResources().forEach(data -> {
            examDetailResources.add(examDetailMapper.toResource(examDetailService.create(examDetailMapper.toModel(data), exam.getId())));
        });

        exam.setExamDetails(examDetailResources);

        return exam;
    }

    @DeleteMapping("{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable Long examId) {
        try {
            return examService.delete(examId);
        } catch (ResourceValidationException | ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
