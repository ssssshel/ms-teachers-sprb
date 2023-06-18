package com.tests.ms_teachers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherCourseDto {
  private Long id_teacher;
  private String teacher_name;
  private Boolean teacher_state;
  private Long id_course;
  private String course_name;
}
