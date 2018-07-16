package com.demo.microservice.toyrobotservice.repository;

import com.demo.microservice.toyrobotservice.entity.ToyRobot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRobotRepository extends JpaRepository<ToyRobot, Long> {

}
