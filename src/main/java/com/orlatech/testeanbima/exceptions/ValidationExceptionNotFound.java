package com.orlatech.testeanbima.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionNotFound extends ValidationException {

    public ValidationExceptionNotFound(String httpStatusName, String message, int httpStatusNumber) {
        super(httpStatusName, message, httpStatusNumber);
    }

}
