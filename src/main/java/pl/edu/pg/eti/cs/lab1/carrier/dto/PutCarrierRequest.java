package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutCarrierRequest {
    private String name;
    private int planeID;

    public static BiFunction<Carrier, PutCarrierRequest, Carrier> dtoToEntityUpdater(
            Function<Integer, Plane> planeFunction){
        return ((carrier, request) -> {
            carrier.setName(request.getName());
            carrier.setPlanes(new ArrayList<Plane>(Arrays.asList(planeFunction.apply(request.getPlaneID()))));
            return carrier;
        });
    }
}
