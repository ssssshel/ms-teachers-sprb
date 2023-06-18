package com.tests.ms_teachers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tests.ms_teachers.model.TeacherModel;

public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {

}
