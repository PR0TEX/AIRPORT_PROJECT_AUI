package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarrierResponse {

    private String name;
    private String nationality;

    public static Function<Carrier, GetCarrierResponse> entityToDtoMapper() {
        return carrier -> GetCarrierResponse.builder()
                .name(carrier.getName())
                .nationality(carrier.getNationality())
                .build();
    }
}
