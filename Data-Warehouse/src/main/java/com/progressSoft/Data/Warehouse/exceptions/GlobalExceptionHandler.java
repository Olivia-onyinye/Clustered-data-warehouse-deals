package com.progressSoft.Data.Warehouse.exceptions;

import com.progressSoft.Data.Warehouse.utils.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DealRequestAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleDealRequestAlreadyExist(DealRequestAlreadyExistException xe){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(409, xe.getMessage(), new Date());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException xe){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(409, xe.getMessage() , new Date());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handlePersonAlreadyExist(IllegalArgumentException xe){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(400, xe.getMessage() , new Date());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }



}
