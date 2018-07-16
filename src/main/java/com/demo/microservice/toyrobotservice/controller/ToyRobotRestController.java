package com.demo.microservice.toyrobotservice.controller;

import com.demo.microservice.toyrobotservice.boundary.ApiResponse;
import com.demo.microservice.toyrobotservice.boundary.Rotation;
import com.demo.microservice.toyrobotservice.boundary.ToyRobotDTO;
import com.demo.microservice.toyrobotservice.entity.ToyRobot;
import com.demo.microservice.toyrobotservice.exception.MissingRobotException;
import com.demo.microservice.toyrobotservice.exception.MovementNotPermittedException;
import com.demo.microservice.toyrobotservice.repository.ToyRobotRepository;
import com.demo.microservice.toyrobotservice.service.ToyRobotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("toy-robots")
@Api(tags = "toy-robots")
public class ToyRobotRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ToyRobotService toyRobotService;

    @Autowired
    ToyRobotRepository toyRobotRepository;

    @PostMapping(path = "place")
    @ApiOperation(value = "Places the toy robot in a table")
    public ResponseEntity<ApiResponse> placeToyRobot(@RequestBody @Validated ToyRobotDTO toyRobotDTO) throws MovementNotPermittedException {

        ToyRobot toyRobot = objectMapper.convertValue(toyRobotDTO, ToyRobot.class);
        ToyRobot toyRobotSaved = toyRobotService.place(toyRobot);

        ApiResponse apiResponse = new ApiResponse("Toy Robot Placed Successfully", objectMapper.convertValue(toyRobotSaved, ToyRobotDTO.class));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "{toyRobotId}/left")
    @ApiOperation(value = "Rotates the toy robot to left")
    public ResponseEntity<ApiResponse> rotateLeft(@PathVariable Long toyRobotId) throws MissingRobotException {
        Optional<ToyRobot> byId = toyRobotRepository.findById(toyRobotId);
        if (!byId.isPresent()) {
            throw new MissingRobotException("Robot is missing");
        }
        ToyRobot savedToyRobot = toyRobotService.rotate(byId.get(), Rotation.LEFT);
        return new ResponseEntity<>(new ApiResponse("Toy Robot rotated left successfully", objectMapper.convertValue(savedToyRobot, ToyRobotDTO.class)), HttpStatus.OK);
    }

    @GetMapping(path = "{toyRobotId}/right")
    @ApiOperation(value = "Rotates the toy robot to right")
    public ResponseEntity<ApiResponse> rotateRight(@PathVariable Long toyRobotId) throws MissingRobotException {
        Optional<ToyRobot> byId = toyRobotRepository.findById(toyRobotId);
        if (!byId.isPresent()) {
            throw new MissingRobotException("Robot is missing");
        }
        ToyRobot savedToyRobot = toyRobotService.rotate(byId.get(), Rotation.RIGHT);
        return new ResponseEntity<>(new ApiResponse("Toy Robot rotated right successfully", objectMapper.convertValue(savedToyRobot, ToyRobotDTO.class)), HttpStatus.OK);
    }

    @GetMapping(path = "{toyRobotId}/move")
    @ApiOperation(value = "Moves the robot straight")
    public ResponseEntity<ApiResponse> move(@PathVariable Long toyRobotId) throws MissingRobotException, MovementNotPermittedException {
        Optional<ToyRobot> byId = toyRobotRepository.findById(toyRobotId);
        if (!byId.isPresent()) {
            throw new MissingRobotException("Robot is missing");
        }
        ToyRobot savedToyRobot = toyRobotService.move(byId.get());
        return new ResponseEntity<>(new ApiResponse("Toy Robot rotated right successfully", objectMapper.convertValue(savedToyRobot, ToyRobotDTO.class)), HttpStatus.OK);
    }

    @GetMapping(path = "{toyRobotId}/report")
    @ApiOperation(value = "Reports the coordinates and face of robot")
    public ResponseEntity<ApiResponse> report(@PathVariable Long toyRobotId) throws MissingRobotException {
        Optional<ToyRobot> byId = toyRobotRepository.findById(toyRobotId);
        if (!byId.isPresent()) {
            throw new MissingRobotException("Robot is missing");
        }
        return new ResponseEntity<>(new ApiResponse("Robot Report", objectMapper.convertValue(byId.get(), ToyRobotDTO.class)), HttpStatus.OK);
    }
}
