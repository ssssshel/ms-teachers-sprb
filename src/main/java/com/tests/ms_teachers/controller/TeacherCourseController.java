package com.tests.ms_teachers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherCourseDto;
import com.tests.ms_teachers.service.TeacherCourseService;

@RestController
@RequestMapping("/v1/teacher-courses")
public class TeacherCourseController {
  @Autowired
  private TeacherCourseService teacherCourseService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllTeacherCourse() {
    ResponseDto response = teacherCourseService.getTeacherCourses();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getTeacherCourseById(@PathVariable("id") Long id) {
    ResponseDto response = teacherCourseService.getTeacherCourseById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping
  public ResponseEntity<ResponseDto> createTeacherCourse(@RequestBody TeacherCourseDto teacherCourse) {
    ResponseDto response = teacherCourseService.createTeacherCourse(teacherCourse);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
