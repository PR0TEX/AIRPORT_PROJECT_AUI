package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
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
public class GetAllPlanesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class PlaneEntry{
        private int id;
        private String manufacturer;
    }

    @Singular
    private List<PlaneEntry> planes;

    public static Function<Collection<Plane>, GetAllPlanesResponse> entityToDtoMapper() {
        return planes -> {
            GetAllPlanesResponseBuilder response = GetAllPlanesResponse.builder();
            planes.stream()
                    .map(plane -> PlaneEntry.builder()
                        .id(plane.getId())
                        .manufacturer(plane.getManufacturer())
                        .build())
                    .forEach(response::plane);
            return response.build();
        };
    }

}
