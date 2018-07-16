package com.demo.microservice.toyrobotservice.entity;

import com.demo.microservice.toyrobotservice.boundary.Face;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class ToyRobot extends AbstractPersistable<Long> {

    @Column
    @Min(value = 0, message = "xCoordinate must be greater than 0")
    @Max(value = 5, message = "xCoordinate must be smaller than 5")
    private Integer xCoordinate;

    @Column
    @Min(value = 0, message = "xCoordinate must be greater than 0")
    @Max(value = 5, message = "yCoordinate must be smaller than 5")
    private Integer yCoordinate;

    @Column
    @Enumerated(EnumType.STRING)
    private Face face;

    public ToyRobot() {
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }
}
