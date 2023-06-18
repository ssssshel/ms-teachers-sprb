package com.tests.ms_teachers.service.inter;

import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherCourseDto;

public interface TeacherCourseServiceInter {
  public ResponseDto getTeacherCourses();

  public ResponseDto getTeacherCourseById(Long id);

  public ResponseDto createTeacherCourse(TeacherCourseDto teacherCourse);
}
