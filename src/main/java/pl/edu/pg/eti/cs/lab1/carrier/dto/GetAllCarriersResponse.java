package pl.edu.pg.eti.cs.lab1.carrier.dto;

import lombok.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;

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
public class GetAllCarriersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class CarrierEntry{
        private String name;
    }
    @Singular
    private List<CarrierEntry> carriers;

    public static Function<Collection<CarrierEntry>, GetAllCarriersResponse> entityToDtoMapper() {
        return carriers -> {
            GetAllCarriersResponseBuilder response = GetAllCarriersResponse.builder();
            carriers.stream()
                    .map(carrier -> CarrierEntry.builder()
                        .name(carrier.getName())
                        .build())
                    .forEach(response::carrier);
            return response.build();
        };
    }
}
