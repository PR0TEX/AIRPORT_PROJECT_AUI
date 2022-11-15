package pl.edu.pg.eti.cs.lab1.carrier.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "carriers")
public class Carrier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String name;
    private String nationality;

    //private List<String> flightDestinations;
    @OneToMany(mappedBy = "carrier")
    private List<Plane> planes;

    public void setPlanes() {
    }
}
