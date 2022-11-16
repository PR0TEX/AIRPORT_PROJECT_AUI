package pl.edu.pg.eti.cs.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.cs.lab1.carrier.dto.GetAllPlanesResponse;
import pl.edu.pg.eti.cs.lab1.carrier.dto.GetPlaneResponse;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PostPlaneRequest;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PutPlaneRequest;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;

import java.util.Optional;

public class PlaneController {
    private final PlaneService planeService;
    private final CarrierService carrierService;
    @Autowired
    public PlaneController(PlaneService planeService, CarrierService carrierService){
        this.planeService = planeService;
        this.carrierService = carrierService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPlaneResponse> getPlane(@PathVariable("id") int id){
        return planeService.find(id)
                .map(plane -> ResponseEntity.ok(GetPlaneResponse.entityToDtoMapper().apply(plane)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetAllPlanesResponse> getPlanes(){
        return ResponseEntity.ok(GetAllPlanesResponse.entityToDtoMapper().apply(planeService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> postPlane(@RequestBody PostPlaneRequest request,
                                          UriComponentsBuilder builder) {
        Plane plane = PostPlaneRequest
                .dtoToEntityMapper()
                .apply(request);
        plane = planeService.create(plane);
        return ResponseEntity.created(builder.pathSegment("api","planes","{id}")
                .buildAndExpand(plane.getId()).toUri()).build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putPlane(@RequestBody PutPlaneRequest request, @PathVariable("id") int id){
        Optional<Plane> plane = planeService.find(id);
        if(plane.isPresent()) {
            PutPlaneRequest
                    .dtoToEntityUpdater(carrierId -> carrierService.find(carrierId).orElseThrow())
                    .apply(plane.get(), request);
            planeService.update(plane.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable("id") int id) {
        Optional<Plane> plane = planeService.find(id);
        if (plane.isPresent()) {
            planeService.delete(plane.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
