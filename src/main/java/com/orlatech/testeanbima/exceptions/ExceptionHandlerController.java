package com.orlatech.testeanbima.exceptions;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(ValidationExceptionNotFound.class)
  public ResponseEntity<ApiError> handleValidationExceptionNotFound(ValidationExceptionNotFound e) {

    ApiError error = new ApiError(e.getHttpStatusName(), e.getMessage(), e.getHttpStatusNumber());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

}
