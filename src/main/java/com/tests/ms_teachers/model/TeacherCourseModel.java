package com.tests.ms_teachers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_courses", schema = "tests")
public class TeacherCourseModel {
  @Id
  @Column(name = "id_teacher")
  private Long idTeacher;

  @Column(name = "id_course")
  private Long idCourse;
}
