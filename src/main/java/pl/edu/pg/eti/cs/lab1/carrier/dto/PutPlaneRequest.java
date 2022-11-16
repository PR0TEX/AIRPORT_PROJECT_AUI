package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPlaneRequest {
    private int carrierId;
    private int maxWeightPayload;

    public static BiFunction<Plane, PutPlaneRequest, Plane> dtoToEntityUpdater(
            Function<Integer, Carrier> carrierFunction) {
        return (plane, request) -> {
            plane.setMaxWeightPayload(request.getMaxWeightPayload());
            //TODO maybe creation of plane
            plane.setCarrier(carrierFunction.apply(request.getCarrierId()));
            return plane;
        };
    }
}
