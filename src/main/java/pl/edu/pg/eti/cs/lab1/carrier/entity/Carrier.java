package pl.edu.pg.eti.cs.lab1.carrier.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Carrier implements Serializable {
    private String name;
    private String nationality;
    private List<String> flightDestinations;
    private List<Plane> planes;
}
