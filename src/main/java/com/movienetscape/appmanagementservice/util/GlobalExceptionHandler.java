package com.movienetscape.appmanagementservice.util;


import com.movienetscape.appmanagementservice.dto.request.BadRequestField;
import com.movienetscape.appmanagementservice.dto.response.BadRequestFieldResponse;
import com.movienetscape.appmanagementservice.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RecordExistException.class)
    public ResponseEntity<ErrorResponse> handleRecordAlreadyExist(RecordExistException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestFieldResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<BadRequestField> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new BadRequestField(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        BadRequestFieldResponse badRequestResponse = BadRequestFieldResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .errors(errors)
                .build();

        return new ResponseEntity<>(badRequestResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something went wrong"));
    }
}
