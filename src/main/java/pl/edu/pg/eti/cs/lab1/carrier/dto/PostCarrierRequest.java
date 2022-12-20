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
public class PostCarrierRequest {
    private String name;
    private String nationality;
    public static Function<PostCarrierRequest, Carrier> dtoToEntityMapper(){
        return request -> Carrier.builder()
                .name(request.getName())
                .nationality(request.getNationality())
                .build();
    }
}
