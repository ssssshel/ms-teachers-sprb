package com.tests.ms_teachers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tests.ms_teachers.dto.ResponseDto;
import com.tests.ms_teachers.dto.TeacherDto;
import com.tests.ms_teachers.model.TeacherModel;
import com.tests.ms_teachers.repository.TeacherRepository;
import com.tests.ms_teachers.service.inter.TeacherServiceInter;
import com.tests.ms_teachers.shared.Utils;

@Service
public class TeacherService implements TeacherServiceInter {
  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  public ResponseDto getAllTeachers() {
    try {
      List<TeacherModel> teachersList = teacherRepository.findAll();
      if (teachersList.isEmpty()) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, teachersList, "No teachers found", false);
      }

      List<TeacherDto> teachersDtoList = new ArrayList<TeacherDto>();
      for (TeacherModel teacherObject : teachersList) {
        teachersDtoList.add(TeacherDto.builder()
            .id(teacherObject.getId())
            .name(teacherObject.getName())
            .surname(teacherObject.getSurname())
            .gender(teacherObject.getGender())
            .state(teacherObject.getState())
            .build());
      }
      return Utils.getResponse(HttpStatus.OK, teachersDtoList, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto getTeacherById(Long id) {
    try {
      TeacherModel teacherModel = teacherRepository.findById(id).orElse(null);
      if (teacherModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }

      TeacherDto teacherDto = TeacherDto.builder()
          .id(teacherModel.getId())
          .name(teacherModel.getName())
          .surname(teacherModel.getSurname())
          .gender(teacherModel.getGender())
          .state(teacherModel.getState())
          .build();

      return Utils.getResponse(HttpStatus.OK, teacherDto, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto createTeacher(TeacherDto teacher) {
    try {
      TeacherModel teacherModel = TeacherModel.builder()
          .id(teacher.getId())
          .name(teacher.getName())
          .surname(teacher.getSurname())
          .gender(teacher.getGender())
          .state(teacher.getState())
          .build();

      teacherRepository.save(teacherModel);
      teacher.setId(teacherModel.getId());
      return Utils.getResponse(HttpStatus.CREATED, teacher, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto updateTeacher(TeacherDto teacher) {
    try {
      TeacherModel teacherModel = teacherRepository.findById(teacher.getId()).orElse(null);
      if (teacherModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }

      teacherModel.setName(teacher.getName());
      teacherModel.setSurname(teacher.getSurname());
      teacherModel.setGender(teacher.getGender());
      teacherModel.setState(teacher.getState());

      teacherRepository.save(teacherModel);
      return Utils.getResponse(HttpStatus.OK, teacher, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }

  @Override
  public ResponseDto deleteTeacher(Long id) {
    try {
      TeacherModel teacherModel = teacherRepository.findById(id).orElse(null);
      if (teacherModel == null) {
        return Utils.getResponse(HttpStatus.NOT_FOUND, null, false);
      }

      teacherModel.setState(false);
      teacherRepository.save(teacherModel);
      return Utils.getResponse(HttpStatus.OK, null, true);
    } catch (Exception e) {
      return Utils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), false);
    }
  }
}
