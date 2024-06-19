package com.exam.admin.resource.exam_detail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateExamDetailResource {
    @NotNull
    @NotBlank
    private String question;
    private int correctOptionOrder;
    private List<String> options;
}
