package com.app.orderapi.config;

import com.app.orderapi.exceptions.GeneralServiceException;
import com.app.orderapi.exceptions.NoDataFoundException;
import com.app.orderapi.exceptions.ValidateServiceException;
import com.app.orderapi.utils.WrapperResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Esta clase sirve para como el usuario recibe los errores
 */

@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerConfig.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request) {
        logger.error(e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
        logger.info(e.getMessage(), e);
        WrapperResponse<ValidateServiceException> response = new WrapperResponse<>(false,e.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
        logger.info(e.getMessage(), e);
        WrapperResponse<ValidateServiceException> response = new WrapperResponse<>(false,e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
        logger.error(e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
