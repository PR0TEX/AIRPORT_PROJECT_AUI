package pl.edu.pg.eti.cs.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.cs.lab1.carrier.dto.*;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.service.CarrierService;
import pl.edu.pg.eti.cs.lab1.plane.service.PlaneService;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/carriers")
public class CarrierController {
    private final CarrierService carrierService;
    private final PlaneService planeService;
    @Autowired
    public CarrierController(CarrierService carrierService, PlaneService planeService){
        this.carrierService = carrierService;
        this.planeService = planeService;
    }

    @GetMapping
    public ResponseEntity<GetAllCarriersResponse> getCarriers() {
        return ResponseEntity.ok(GetAllCarriersResponse.entityToDtoMapper().apply(carrierService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCarrierResponse> getCarrier(@PathVariable("id") int id){
        return carrierService.find(id)
                .map(carrier -> ResponseEntity.ok(GetCarrierResponse.entityToDtoMapper().apply(carrier)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postCarrier(@RequestBody PostCarrierRequest request, UriComponentsBuilder builder){
        Carrier carrier = PostCarrierRequest
                .dtoToEntityMapper()
                .apply(request);
        carrier = carrierService.create(carrier);
        return ResponseEntity.created(builder.pathSegment("api","carriers","{name}")
                .buildAndExpand(carrier.getName()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCarrier(@PathVariable("id") int id){
        Optional<Carrier> carrier = carrierService.find(id);
        if(carrier.isPresent()){
            carrierService.delete(carrier.get().getId());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putCarrier(@RequestBody PutCarrierRequest request,
                                           @PathVariable("id") int id){
        Optional<Carrier> carrier = carrierService.find(id);
        if(carrier.isPresent()){
            PutCarrierRequest
                    .dtoToEntityUpdater(planeId -> planeService.find(planeId).orElseThrow())
                    .apply(carrier.get(), request);
            carrierService.update(carrier.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/planes")
    public ResponseEntity<GetAllPlanesResponse> getCarrierPlane(@PathVariable("id") int id){
        Optional<Carrier> carrierPromise = carrierService.find(id);
        return carrierPromise.map(value -> ResponseEntity.ok(GetAllPlanesResponse.entityToDtoMapper().apply(planeService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
