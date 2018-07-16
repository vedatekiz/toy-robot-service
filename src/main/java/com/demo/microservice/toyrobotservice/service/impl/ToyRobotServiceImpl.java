package com.demo.microservice.toyrobotservice.service.impl;

import com.demo.microservice.toyrobotservice.boundary.Face;
import com.demo.microservice.toyrobotservice.boundary.Rotation;
import com.demo.microservice.toyrobotservice.entity.ToyRobot;
import com.demo.microservice.toyrobotservice.exception.MovementNotPermittedException;
import com.demo.microservice.toyrobotservice.repository.ToyRobotRepository;
import com.demo.microservice.toyrobotservice.service.ToyRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ToyRobotServiceImpl implements ToyRobotService {

    @Value("${table.dimension.max}")
    private Integer tableDimensionMax;

    @Value("${table.dimension.min}")
    private Integer tableDimensionMin;

    @Autowired
    ToyRobotRepository toyRobotRepository;

    @Override
    public ToyRobot place(ToyRobot toyRobot) throws MovementNotPermittedException {
        if (checkCoordinates(toyRobot)) {
            throw new MovementNotPermittedException("X And Y coordinates must be between " + tableDimensionMin + " and " + tableDimensionMax);
        }

        return toyRobotRepository.save(toyRobot);
    }

    @Override
    public ToyRobot rotate(ToyRobot toyRobot, Rotation rotation) {
        Face face = toyRobot.getFace();
        if (face.equals(Face.EAST)) {
            if (rotation.equals(Rotation.LEFT)) {
                toyRobot.setFace(Face.NORTH);
            } else if (rotation.equals(Rotation.RIGHT)) {
                toyRobot.setFace(Face.SOUTH);
            }
        } else if (face.equals(Face.WEST)) {
            if (rotation.equals(Rotation.LEFT)) {
                toyRobot.setFace(Face.SOUTH);
            } else if (rotation.equals(Rotation.RIGHT)) {
                toyRobot.setFace(Face.NORTH);
            }
        } else if (face.equals(Face.NORTH)) {
            if (rotation.equals(Rotation.LEFT)) {
                toyRobot.setFace(Face.WEST);
            } else if (rotation.equals(Rotation.RIGHT)) {
                toyRobot.setFace(Face.EAST);
            }
        } else if (face.equals(Face.SOUTH)) {
            if (rotation.equals(Rotation.LEFT)) {
                toyRobot.setFace(Face.EAST);
            } else if (rotation.equals(Rotation.RIGHT)) {
                toyRobot.setFace(Face.WEST);
            }
        }

        return toyRobotRepository.save(toyRobot);
    }

    @Override
    public ToyRobot move(ToyRobot toyRobot) throws MovementNotPermittedException {
        Face face = toyRobot.getFace();
        if (face.equals(Face.EAST)) {
            toyRobot.setxCoordinate(toyRobot.getxCoordinate() + 1);
        } else if (face.equals(Face.WEST)) {
            toyRobot.setxCoordinate(toyRobot.getxCoordinate() - 1);
        } else if (face.equals(Face.NORTH)) {
            toyRobot.setyCoordinate(toyRobot.getyCoordinate() + 1);
        } else if (face.equals(Face.SOUTH)) {
            toyRobot.setyCoordinate(toyRobot.getyCoordinate() - 1);
        }
        if (checkCoordinates(toyRobot)) {
            throw new MovementNotPermittedException("Movement Not Permitted. Movement Causes the robot to fall!");
        }
        return toyRobotRepository.save(toyRobot);
    }

    private Boolean checkCoordinates(ToyRobot toyRobot) {
        return toyRobot.getxCoordinate() < tableDimensionMin || toyRobot.getxCoordinate() > tableDimensionMax ||
                toyRobot.getyCoordinate() < tableDimensionMin || toyRobot.getyCoordinate() > tableDimensionMax;
    }
}
