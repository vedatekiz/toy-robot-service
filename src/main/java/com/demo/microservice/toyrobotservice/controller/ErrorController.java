package com.demo.microservice.toyrobotservice.controller;

import com.demo.microservice.toyrobotservice.boundary.ApiResponse;
import com.demo.microservice.toyrobotservice.exception.MissingRobotException;
import com.demo.microservice.toyrobotservice.exception.MovementNotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({MovementNotPermittedException.class, MissingRobotException.class})
    public ResponseEntity<ApiResponse> handleMovementNotPermittedException(Exception e) {
        logger.warn("Method Arguments Not Valid");
        return new ResponseEntity<>(new ApiResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
    }
}
