package pl.edu.pg.eti.cs.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

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
        Plane p1 = Plane.builder().manufacturer("Boeing").model("737").maxWeightPayload(10).maxPeoplePayload(150).build();
//        planeService.create("Boeing", "737", 10, 150);
        planeService.create(p1);
        planeService.create("Boeing", "747", 15, 200);
//        planeService.create("Boeing", "737", 10, 150);
//        planeService.create("Airbus", "A300", 8, 120);
//        planeService.create("Boeing", "737", 15, 280);

        carrierService.create(
                "ryanair",
                "Ireland",
                new ArrayList<String>(Arrays.asList("Poland", "England")),
                new ArrayList<Plane>(Arrays.asList(p1))
        );
//        carrierService.create(
//                "lufthansa",
//                "Germany",
//                new ArrayList<String>(Arrays.asList("Paris", "London")),
//                new ArrayList<Plane>(Arrays.asList(planeService.findAll().get(2), planeService.findAll().get(3)))
//        );
//        carrierService.create(
//                "lot",
//                "Poland",
//                new ArrayList<String>(Arrays.asList("Poland", "Spain")),
//                new ArrayList<Plane>()
//        );
    }
}
