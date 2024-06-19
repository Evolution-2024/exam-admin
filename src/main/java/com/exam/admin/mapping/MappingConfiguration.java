package com.exam.admin.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("adminMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ExamMapper examMapper() {return new ExamMapper();}

    @Bean
    public ExamDetailMapper examDetailMapper() {return new ExamDetailMapper();}
}
