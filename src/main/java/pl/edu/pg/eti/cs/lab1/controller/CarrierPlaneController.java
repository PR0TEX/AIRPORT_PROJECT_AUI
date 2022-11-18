package pl.edu.pg.eti.cs.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.cs.lab1.carrier.dto.GetAllPlanesResponse;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PostPlaneRequest;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PutPlaneCarrierRequest;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PutPlaneRequest;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("api/carriers/{id}/planes")
public class CarrierPlaneController {
    private CarrierService carrierService;
    private PlaneService planeService;

    @Autowired
    public CarrierPlaneController(CarrierService carrierService, PlaneService planeService){
        this.carrierService = carrierService;
        this.planeService = planeService;
    }

    @GetMapping
    public ResponseEntity<GetAllPlanesResponse> getPLanes(@PathVariable("id") int id) {
        Optional<Carrier> carrier = carrierService.find(id);
        return carrier.map(value -> ResponseEntity.ok(GetAllPlanesResponse.entityToDtoMapper().apply(planeService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPlane(@PathVariable("id") int id,
                                          @RequestBody PostPlaneRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Carrier> carrier = carrierService.find(id);
        if(carrier.isPresent()) {
            Plane plane = PostPlaneRequest
                    .dtoToEntityMapper(carrier::get)
                    .apply(request);
            plane = planeService.create(plane);
            return ResponseEntity.created(builder.pathSegment("api", "carriers", "{id}", "planes", "{id}")
                    .buildAndExpand(carrier.get().getName(), plane.getId()).toUri()).build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{plane}")
    public ResponseEntity<Void> puPlane(@PathVariable("id") int id,
                                        @RequestBody PutPlaneRequest request,
                                        @PathVariable("plane") int planeId) {
        Optional<Plane> plane = planeService.find(id, planeId);
        if(plane.isPresent()){
            PutPlaneRequest.dtoToEntityUpdater().apply(plane.get(), request);
            planeService.update(plane.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{plane}")
    public ResponseEntity<Void> deletePlane(@PathVariable("id") int id,
                                            @PathVariable("plane") int planeId) {
        Optional<Plane> plane = planeService.find(id, planeId);
        if(plane.isPresent()){
            planeService.delete(plane.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
