package pl.edu.pg.eti.cs.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitializedData {
    private final PlaneService planeService;
    private final CarrierService carrierService;

    @Autowired
    InitializedData(PlaneService planeService, CarrierService carrierService) {
        this.planeService = planeService;
        this.carrierService = carrierService;
    }

    @PostConstruct
    private synchronized void init() {
        Plane boeing737 = Plane.builder()
                .id(0)
                .manufacturer("Boeing")
                .model("737")
                .maxWeightPayload(10)
                .maxPeoplePayload(150)
                .build();
        Plane boeing747 = Plane.builder()
                .id(1)
                .manufacturer("Boeing")
                .model("747")
                .maxWeightPayload(15)
                .maxPeoplePayload(200)
                .build();
        Plane boeing787 = Plane.builder()
                .id(2)
                .manufacturer("Boeing")
                .model("737")
                .maxWeightPayload(10)
                .maxPeoplePayload(150)
                .build();
        Plane airbusA300 = Plane.builder()
                .id(3)
                .manufacturer("Airbus")
                .model("A300")
                .maxWeightPayload(8)
                .maxPeoplePayload(120)
                .build();
        Plane airbusA310 = Plane.builder()
                .id(4)
                .manufacturer("Airbus")
                .model("A310")
                .maxWeightPayload(15)
                .maxPeoplePayload(280)
                .build();

        planeService.create(boeing737);
        planeService.create(boeing747);
        planeService.create(boeing787);
        planeService.create(airbusA300);
        planeService.create(airbusA310);

        Carrier ryanair = Carrier.builder()
                .name("ryanair")
                .nationality("Ireland")
                .flightDestinations(List.of("Poland", "England"))
                .planes(List.of(boeing737, boeing747))
                .build();
        Carrier lufthansa = Carrier.builder()
                .name("lufthansa")
                .nationality("Germany")
                .flightDestinations(List.of("Paris", "London"))
                .planes(List.of(boeing787, airbusA300, airbusA310))
                .build();
        Carrier lot = Carrier.builder()
                .name("lot")
                .nationality("Poland")
                .flightDestinations(List.of("Poland", "Spain"))
                .planes(List.of())
                .build();

        carrierService.create(ryanair);
        carrierService.create(lufthansa);
        carrierService.create(lot);

    }
}
