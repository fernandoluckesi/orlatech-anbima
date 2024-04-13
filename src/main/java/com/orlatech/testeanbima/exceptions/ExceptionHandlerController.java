package com.orlatech.testeanbima.exceptions;

import org.springframework.context.MessageSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orlatech.testeanbima.dtos.ApiError;

import lombok.Builder;

@ControllerAdvice
@Builder
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(ValidationExceptionBadRequest.class)
  public ResponseEntity<ApiError> handleValidationExceptionUnder18YearsOld(ValidationExceptionBadRequest e) {

    ApiError error = new ApiError(e.getHttpStatusName(), e.getMessage(), e.getHttpStatusNumber());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ValidationExceptionConflict.class)
  public ResponseEntity<ApiError> handleValidationExceptionConflict(ValidationExceptionConflict e) {

    ApiError error = new ApiError(e.getHttpStatusName(), e.getMessage(), e.getHttpStatusNumber());
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ValidationExceptionNotFound.class)
  public ResponseEntity<ApiError> handleValidationExceptionNotFound(ValidationExceptionNotFound e) {

    ApiError error = new ApiError(e.getHttpStatusName(), e.getMessage(), e.getHttpStatusNumber());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

}
