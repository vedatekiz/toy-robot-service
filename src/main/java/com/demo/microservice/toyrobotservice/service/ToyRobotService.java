package com.demo.microservice.toyrobotservice.service;

import com.demo.microservice.toyrobotservice.boundary.Rotation;
import com.demo.microservice.toyrobotservice.entity.ToyRobot;
import com.demo.microservice.toyrobotservice.exception.MovementNotPermittedException;

public interface ToyRobotService {

    ToyRobot place(ToyRobot toyRobot) throws MovementNotPermittedException;

    ToyRobot rotate(ToyRobot toyRobot, Rotation rotation);

    ToyRobot move(ToyRobot toyRobot) throws MovementNotPermittedException;

}
