package pl.edu.pg.eti.cs.lab1.plane.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Plane implements Serializable {
    private int id;
    private String manufacturer;
    private String model;
    private int maxWeightPayload;
    private int maxPeoplePayload;
}
