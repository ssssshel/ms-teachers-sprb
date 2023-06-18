package com.tests.ms_teachers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherDto;
import com.tests.ms_teachers.service.TeacherService;

@RestController
@RequestMapping("/v1/teachers")
public class TeacherController {
  @Autowired
  private TeacherService teacherService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllTeachers() {
    ResponseDto response = teacherService.getAllTeachers();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getTeacherById(@PathVariable("id") Long id) {
    ResponseDto response = teacherService.getTeacherById(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createTeacher(@RequestBody TeacherDto teacher) {
    ResponseDto response = teacherService.createTeacher(null);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateTeacher(@RequestBody TeacherDto teacher) {
    ResponseDto response = teacherService.updateTeacher(null);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteTeacher(@PathVariable("id") Long id) {
    ResponseDto response = teacherService.deleteTeacher(id);
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }
}
