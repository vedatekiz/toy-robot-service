package com.demo.microservice.toyrobotservice.exception;

public class MovementNotPermittedException extends Exception {

    public MovementNotPermittedException(String message) {
        super(message);
    }
}
