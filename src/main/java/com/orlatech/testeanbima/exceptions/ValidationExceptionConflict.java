package com.orlatech.testeanbima.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionConflict extends ValidationException {

  public ValidationExceptionConflict(String httpStatusName, String message, int httpStatusNumber) {
    super(httpStatusName, message, httpStatusNumber);
  }

}
