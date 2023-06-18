package com.tests.ms_teachers.shared;

import org.springframework.http.HttpStatus;

import com.tests.ms_teachers.dto.ResponseDto;

public class Utils {
  public static ResponseDto getResponse(HttpStatus httpStatus, Object data, boolean success) {
    ResponseDto response = new ResponseDto();

    response.setHttpStatus(httpStatus.value());
    response.setServerMessage(httpStatus.getReasonPhrase());
    response.setData(data);
    response.setSuccess(success);
    response.setError(!success);

    return response;
  }

  public static ResponseDto getResponse(HttpStatus httpStatus, Object data, String serverMessage, boolean success) {
    ResponseDto response = new ResponseDto();

    response.setHttpStatus(httpStatus.value());
    response.setServerMessage(serverMessage);
    response.setData(data);
    response.setSuccess(success);
    response.setError(!success);
    response.setServerMessage(serverMessage);

    return response;
  }

}
