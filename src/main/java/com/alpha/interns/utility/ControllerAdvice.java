package com.alpha.interns.utility;

import com.alpha.interns.exception.AlphaInternException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private static final Log LOGGER = LogFactory.getLog(ControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidations(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        ErrorResponse errorResponse = new ErrorResponse();

        for(FieldError error : bindingResult.getFieldErrors()){
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.addError(fieldName, errorMessage);
        }
        LOGGER.error(exception.getMessage(),exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }
    @ExceptionHandler(AlphaInternException.class)
    public ResponseEntity<String> errorLogging(AlphaInternException e){
        LOGGER.error(e.getMessage(),e);
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> otherError(Exception e){
        String str = e.getMessage();
        LOGGER.error(str);
        return new ResponseEntity<>(str,HttpStatus.BAD_REQUEST);
    }

}
