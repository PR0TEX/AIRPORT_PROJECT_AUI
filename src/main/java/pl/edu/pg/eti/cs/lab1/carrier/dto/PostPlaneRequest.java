package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostPlaneRequest {
    private String manufacturer;
    private String model;
    private int maxWeightPayload;
    private int maxPeoplePayload;

    public static Function<PostPlaneRequest, Plane> dtoToEntityMapper(){
        return request -> Plane.builder()
                .manufacturer(request.getManufacturer())
                .model(request.getModel())
                .maxWeightPayload(request.getMaxWeightPayload())
                .maxPeoplePayload(request.getMaxPeoplePayload())
                .build();
    }
}
