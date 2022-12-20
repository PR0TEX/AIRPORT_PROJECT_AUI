package pl.edu.pg.eti.cs.lab1.carrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.cs.lab1.carrier.dto.PostCarrierRequest;
import pl.edu.pg.eti.cs.lab1.carrier.entity.Carrier;
import pl.edu.pg.eti.cs.lab1.carrier.repository.ICarrierRepository;
import pl.edu.pg.eti.cs.lab1.plane.entity.Plane;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {
    private final ICarrierRepository repository;

    @Autowired
    CarrierService(ICarrierRepository carrierRepository) {
        this.repository = carrierRepository;
    }

    public Optional<Carrier> find(int id) {
        return repository.findById(id);
    }

    public List<Carrier> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(String name, String nationality, List<String> flightDestinations, List<Plane> planes) {
        Carrier carrier = Carrier.builder()
                .name(name)
                .nationality(nationality)
                .planes(planes)
                .build();

        create(carrier);
    }

    public List<Plane> getNewPlaneList(Carrier carrier, Plane plane){
        List<Plane> planesWithNewOne;
        if(carrier.getPlanes() == null){
            planesWithNewOne = new ArrayList<>();
        }else{
            planesWithNewOne = carrier.getPlanes();
        }
        planesWithNewOne.add(plane);
        return planesWithNewOne;
    }
    @Transactional
    public Carrier create(Carrier carrier) {
       return repository.save(carrier);
    }
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
    @Transactional
    public void update(Carrier carrier) {
        repository.save(carrier);
    }

    @Transactional
    public void updatePlanes(int id, Plane plane) {
        repository.findById(id).ifPresent(carrier -> {
            List<Plane> test = getNewPlaneList(carrier, plane);
            carrier.setPlanes(test);
        });
        update(repository.findById(id).get());
    }
}
