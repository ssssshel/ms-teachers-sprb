package com.tests.ms_teachers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tests.ms_teachers.model.TeacherCourseModel;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourseModel, Long> {
  TeacherCourseModel findByIdTeacher(Long idTeacher);
}
