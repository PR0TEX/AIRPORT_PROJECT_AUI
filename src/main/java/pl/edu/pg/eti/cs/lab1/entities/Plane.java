package pl.edu.pg.eti.cs.lab1.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
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
