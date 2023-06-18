package com.tests.ms_teachers.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tests.ms_teachers.dto.ResponseDto;

@FeignClient(name = "ms-courses", url = "http://localhost:8081/api")
public interface CourseClient {

  @GetMapping("/v1/courses")
  ResponseDto getAllCourses();

  @GetMapping("/v1/courses/{id}")
  ResponseDto getCourseById(@PathVariable("id") Long id);
}
