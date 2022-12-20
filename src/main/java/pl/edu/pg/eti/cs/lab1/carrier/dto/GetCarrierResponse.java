package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarrierResponse {
    private int id;
    private String nationality;

    public static Function<Carrier, GetCarrierResponse> entityToDtoMapper() {
        return carrier -> GetCarrierResponse.builder()
                .id(carrier.getId())
                .nationality(carrier.getNationality())
                .build();
    }
}
