package pl.edu.pg.eti.cs.lab1.plane.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String manufacturer;
    private String model;
    private int maxWeightPayload;
    private int maxPeoplePayload;
    @ManyToOne
    @JoinColumn(name="carrier")
    private Carrier carrier;
}
