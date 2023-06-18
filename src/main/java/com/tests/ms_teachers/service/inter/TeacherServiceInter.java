package com.tests.ms_teachers.service.inter;

import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherDto;

public interface TeacherServiceInter {
  public ResponseDto getAllTeachers();

  public ResponseDto getTeacherById(Long id);

  public ResponseDto createTeacher(TeacherDto teacher);

  public ResponseDto updateTeacher(TeacherDto teacher);

  public ResponseDto deleteTeacher(Long id);

}
