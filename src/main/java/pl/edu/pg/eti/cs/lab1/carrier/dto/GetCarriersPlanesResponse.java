package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarriersPlanesResponse {

    @Singular
    private List<GetAllPlanesResponse.PlaneEntry> planes;

//    public static Function<Collection<Plane>,GetCarriersPlanesResponse> entityToDtoMapper() {
//
//    }
}
