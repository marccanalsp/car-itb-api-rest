package cat.itb.m09_marccanals_apirest_persistent.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Car {
    @Id
    private String idCar;
    private String model;
    private String producer;
    private double price;
    private String maxVel;


}
