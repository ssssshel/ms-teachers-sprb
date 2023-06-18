package com.tests.ms_teachers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
  private Long id;
  private String name;
  private String description;
  private Boolean state;
}
