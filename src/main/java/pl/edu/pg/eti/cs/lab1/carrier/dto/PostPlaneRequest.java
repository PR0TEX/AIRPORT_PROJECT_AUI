package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.function.Function;
import java.util.function.Supplier;

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
    private int carrier;

//    public static Function<PostPlaneRequest, Plane> dtoToEntityMapper(Supplier<Carrier> carrierSupplier){
//        return request -> Plane.builder()
//                .manufacturer(request.getManufacturer())
//                .model(request.getModel())
//                .carrier(carrierSupplier.get())
//                .maxWeightPayload(request.getMaxWeightPayload())
//                .maxPeoplePayload(request.getMaxPeoplePayload())
//                .build();
//    }

    public static Function<PostPlaneRequest, Plane> dtoToEntityMapper(Function<Integer, Carrier> carrierFunction) {
        return request -> Plane.builder()
                .manufacturer(request.getManufacturer())
                .model(request.getModel())
                .carrier(carrierFunction.apply(request.getCarrier()))
                .maxWeightPayload(request.getMaxWeightPayload())
                .maxPeoplePayload(request.getMaxPeoplePayload())
                .build();
    }
}
