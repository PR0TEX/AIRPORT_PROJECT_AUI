package pl.edu.pg.eti.cs.lab1.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
//TODO serializable
public class Carrier implements Serializable {
    private String name;
    private String nationality;
    private List<String> flightDirections;
}
