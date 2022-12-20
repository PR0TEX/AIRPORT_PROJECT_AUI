package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPlaneResponse {
    private int id;
    private String manufacturer;
    private String model;
    private Carrier carrier;

    public static Function<Plane, GetPlaneResponse> entityToDtoMapper(){
        return plane -> GetPlaneResponse.builder()
                .id(plane.getId())
                .manufacturer(plane.getManufacturer())
                .model(plane.getModel())
                .build();
    }
}
