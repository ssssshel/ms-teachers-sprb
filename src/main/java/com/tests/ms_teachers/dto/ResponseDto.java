package com.tests.ms_teachers.dto;

import lombok.Data;

@Data
public class ResponseDto {
  private Integer httpStatus;
  private String serverMessage;
  private Object data;
  private boolean success;
  private boolean error;
}
