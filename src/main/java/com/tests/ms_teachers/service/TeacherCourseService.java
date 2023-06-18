package com.tests.ms_teachers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests.ms_teachers.client.CourseClient;
import com.tests.ms_teachers.dto.CourseDto;
import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherCourseDto;
import com.tests.ms_teachers.model.TeacherCourseModel;
import com.tests.ms_teachers.model.TeacherModel;
import com.tests.ms_teachers.repository.TeacherCourseRepository;
import com.tests.ms_teachers.repository.TeacherRepository;
import com.tests.ms_teachers.service.inter.TeacherCourseServiceInter;
import com.tests.ms_teachers.shared.Utils;

import feign.RetryableException;

@Service
public class TeacherCourseService implements TeacherCourseServiceInter {
  @Autowired
  private TeacherCourseRepository teacherCourseRepository;

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private CourseClient courseClient;

  @Override
  public ResponseDto getTeacherCourses() {
    ObjectMapper mapper = new ObjectMapper();

    try {
      List<TeacherCourseModel> teacherCourseModels = teacherCourseRepository.findAll();

      List<TeacherCourseDto> teacherCourseDtos = new ArrayList<TeacherCourseDto>();

      for (int i = 0; i < teacherCourseModels.size(); i++) {
        TeacherModel teacherModel = teacherRepository.findById(teacherCourseModels.get(i).getIdTeacher()).orElse(null);

        ResponseDto responseDto = courseClient.getCourseById(teacherCourseModels.get(i).getIdCourse());
        CourseDto courseDto = mapper.convertValue(responseDto.getData(), CourseDto.class);

        teacherCourseDtos.add(TeacherCourseDto.builder()
            .id_teacher(teacherModel.getId())
            .teacher_name(teacherModel.getName() + " " + teacherModel.getSurname())
            .teacher_state(teacherModel.getState())
            .id_course(courseDto.getId())
            .course_name(courseDto.getName())
            .build());
      }

      return Utils.getResponse(HttpStatus.OK, teacherCourseDtos, true);
    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.SERVICE_UNAVAILABLE, null, "Service unavailable", false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getTeacherCourseById(Long id) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      TeacherCourseModel teacherCourseModel = teacherCourseRepository.findById(id).orElse(null);
      if (teacherCourseModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }

      TeacherModel teacherModel = teacherRepository.findById(teacherCourseModel.getIdTeacher()).orElse(null);

      ResponseDto responseDto = courseClient.getCourseById(teacherCourseModel.getIdCourse());

      CourseDto courseDto = mapper.convertValue(responseDto.getData(), CourseDto.class);

      TeacherCourseDto teacherCourseDto = TeacherCourseDto.builder()
          .id_teacher(teacherModel.getId())
          .teacher_name(teacherModel.getName() + " " + teacherModel.getSurname())
          .teacher_state(teacherModel.getState())
          .id_course(courseDto.getId())
          .course_name(courseDto.getName())
          .build();

      return Utils.getResponse(HttpStatus.OK, teacherCourseDto, true);
    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.SERVICE_UNAVAILABLE, null, "Service unavailable", false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto createTeacherCourse(TeacherCourseDto teacherCourse) {
    try {

      ResponseDto responseDto = courseClient.getCourseById(teacherCourse.getId_course());

      if (responseDto.isError()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }

      TeacherCourseModel teacherCourseModel = TeacherCourseModel.builder()
          .idTeacher(teacherCourse.getId_teacher())
          .idCourse(teacherCourse.getId_course())
          .build();

      teacherCourseRepository.save(teacherCourseModel);

      return Utils.getResponse(HttpStatus.CREATED, null, true);
    } catch (RetryableException ex) {
      return Utils.getResponse(HttpStatus.SERVICE_UNAVAILABLE, null, "Service unavailable", false);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage(), false);
    }
  }
}
