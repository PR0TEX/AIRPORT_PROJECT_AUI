package pl.edu.pg.eti.cs.lab1.plane.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "planes")
public class Plane implements Serializable {
    @Id
    private int id;
    private String manufacturer;
    private String model;
    private int maxWeightPayload;
    private int maxPeoplePayload;
}
