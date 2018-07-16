package com.demo.microservice.toyrobotservice.exception;

public class MissingRobotException extends Exception {

    public MissingRobotException(String message) {
        super(message);
    }
}
